package com.sshpro.threepeeks.data.repository

import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.domain.model.Photo
import com.sshpro.threepeeks.data.remote.JSONPlaceholderApi
import com.sshpro.threepeeks.data.remote.dto.NetworkEntity
import com.sshpro.threepeeks.data.remote.dto.PhotoNetworkEntity
import com.sshpro.threepeeks.data.remote.dto.defaultPhoto
import com.sshpro.threepeeks.data.remote.dto.defaultUser
import com.sshpro.threepeeks.domain.mappers.Mapper
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AlbumRepositoryImpl @Inject constructor(
    private val api: JSONPlaceholderApi,
    private val networkAlbumMapper: Mapper<NetworkEntity, Album>,
    private val networkPhotoMapper: Mapper<PhotoNetworkEntity, Photo>,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AlbumRepository {

    private val photoEntities by lazy {
        async(coroutineDispatcher) {
            api.getPhotos()
        }
    }

    private fun <T> async(
        coroutineContext: CoroutineContext,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T> {
        return CoroutineScope(coroutineContext).async {
            block()
        }
    }

    override suspend fun getAlbums(): List<Album> {
        val albumEntities = async(coroutineDispatcher) { api.getAlbums() }
        val userEntities = async(coroutineDispatcher) { api.getUsers() }

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
        return albums
    }

    override suspend fun getPhotos(albumId: Int): List<Photo> {
        return photoEntities.await()
            .filter { photo -> photo.albumId == albumId }
            .map { filtered -> networkPhotoMapper.mapToDomain(filtered) }
    }
}