package com.sshpro.threepeeks.business

import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.domain.Photo
import com.sshpro.threepeeks.business.network.NetworkService
import com.sshpro.threepeeks.business.network.data.NetworkEntity
import com.sshpro.threepeeks.business.network.data.PhotoNetworkEntity
import com.sshpro.threepeeks.business.network.data.defaultPhoto
import com.sshpro.threepeeks.business.network.data.defaultUser
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AlbumRepository @Inject constructor(
    private val networkService: NetworkService,
    private val networkAlbumMapper: Mapper<NetworkEntity, Album>,
    private val networkPhotoMapper: Mapper<PhotoNetworkEntity, Photo>,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private val photoEntities by lazy {
        async(coroutineDispatcher) {
            networkService.getPhotos()
        }
    }

    val albumsAsFlow: Flow<DataState<List<Album>>>
        get() = flow {
            emit(DataState.Loading)

            val albumEntities = async(coroutineDispatcher) { networkService.getAlbums() }
            val userEntities = async(coroutineDispatcher) { networkService.getUsers() }

            val albums = albumEntities.await().map { albumEntity ->
                val photo = photoEntities.await().find { photoNetworkEntity ->
                    albumEntity.id == photoNetworkEntity.albumId
                } ?: defaultPhoto()
                val user = userEntities.await().find { userNetworkEntity ->
                    albumEntity.userId == userNetworkEntity.id
                } ?: defaultUser()
                val networkEntity = NetworkEntity(album = albumEntity, photo = photo, user = user)
                networkAlbumMapper.mapToDomain(networkEntity)
            }
            emit(DataState.Success(albums))
        }.catch { exception ->
            emit(DataState.Error(exception))
        }

    fun photos(albumId: Int): Flow<DataState<List<Photo>>> =
        flow {
            emit(DataState.Loading)
            val photos = photoEntities.await()
                .filter { photo -> photo.albumId == albumId }
                .map { filtered -> networkPhotoMapper.mapToDomain(filtered) }
            emit(DataState.Success(photos))
        }.catch { exception ->
            emit(DataState.Error(exception))
        }

    private fun <T> async(
        coroutineContext: CoroutineContext,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T> {
        return CoroutineScope(coroutineContext).async {
            block()
        }
    }
}