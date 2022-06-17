package com.sshpro.threepeeks.business

import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.network.NetworkMapper
import com.sshpro.threepeeks.business.network.NetworkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val networkService: NetworkService,
    private val networkMapper: NetworkMapper,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    val albumsAsFlow: Flow<Album>
    get() = flow {
        val albums = networkService.getAlbums()
        albums.map { albumEntity ->
            val photoEntity = networkService.getPhotos(albumEntity.id).first()
            val album = networkMapper.mapToDomain(albumEntity, photoEntity)
            emit(album)
        }
    }
}