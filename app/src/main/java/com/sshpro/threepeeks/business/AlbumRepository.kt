package com.sshpro.threepeeks.business

import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.network.NetworkMapper
import com.sshpro.threepeeks.business.network.NetworkService
import com.sshpro.threepeeks.business.network.defaultPhoto
import com.sshpro.threepeeks.business.network.defaultUser
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val networkService: NetworkService,
    private val networkMapper: NetworkMapper,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    val albumsAsFlow: Flow<DataState<List<Album>>>
        get() = flow {
            emit(DataState.Loading)

            val albumEntities = async { networkService.getAlbums() }
            val photoEntities = async { networkService.getPhotos() }
            val userEntities = async { networkService.getUsers() }

            val albums = albumEntities.await().map { albumEntity ->
                val photo =  photoEntities.await().find { photoNetworkEntity ->
                    albumEntity.id == photoNetworkEntity.albumId
                } ?: defaultPhoto()
                val user = userEntities.await().find { userNetworkEntity ->
                    albumEntity.userId == userNetworkEntity.id
                } ?: defaultUser()
                 networkMapper.mapToDomain(albumEntity, photo, user)
            }
            emit(DataState.Success(albums))
        }.catch { exception ->
            emit(DataState.Error(exception))
        }

    private fun <T> async(
        block: suspend CoroutineScope.() -> T
    ): Deferred<T> {
        return CoroutineScope(coroutineDispatcher).async {
            block()
        }
    }
}