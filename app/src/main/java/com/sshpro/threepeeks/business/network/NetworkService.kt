package com.sshpro.threepeeks.business.network

interface NetworkService {
    suspend fun getPhotos(): List<PhotoNetworkEntity>
    suspend fun getPhotos(albumId: Int): List<PhotoNetworkEntity>

    suspend fun getAlbums(): List<AlbumNetworkEntity>
}