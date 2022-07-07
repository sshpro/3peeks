package com.sshpro.threepeeks.data.repository

import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.domain.model.Photo

interface AlbumRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getPhotos(albumId: Int): List<Photo>
}