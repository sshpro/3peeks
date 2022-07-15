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

    private val photosJob by lazy {
        async(coroutineDispatcher) {
            api.getPhotos()
        }
    }

    private fun <T> async(
        coroutineContext: CoroutineContext,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T> {
        return CoroutineScope(coroutineContext).async {
            ensureActive()
            block()
        }
    }

    override suspend fun getAlbums(): List<Album> {
        val albumJob = async(coroutineDispatcher) { api.getAlbums() }
        val userJob = async(coroutineDispatcher) { api.getUsers() }
        val albums = albumJob.await().map { albumNetworkEntity ->
            val photo = photosJob.await().find { photoNetworkEntity ->
                albumNetworkEntity.id == photoNetworkEntity.albumId
            } ?: defaultPhoto()
            val user = userJob.await().find { userNetworkEntity ->
                albumNetworkEntity.userId == userNetworkEntity.id
            } ?: defaultUser()
            val networkEntity = NetworkEntity(album = albumNetworkEntity, photo = photo, user = user)
            networkAlbumMapper.mapToDomain(networkEntity)
        }
        return albums
    }

    override suspend fun getPhotos(albumId: Int): List<Photo> {
        return photosJob.await()
            .filter { photo -> photo.albumId == albumId }
            .map { filtered -> networkPhotoMapper.mapToDomain(filtered) }
    }
}