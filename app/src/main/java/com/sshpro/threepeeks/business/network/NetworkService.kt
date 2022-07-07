package com.sshpro.threepeeks.business.network

import com.sshpro.threepeeks.business.network.data.AlbumNetworkEntity
import com.sshpro.threepeeks.business.network.data.PhotoNetworkEntity
import com.sshpro.threepeeks.business.network.data.UserNetworkEntity

interface NetworkService {
    suspend fun getPhotos(): List<PhotoNetworkEntity>
    suspend fun getPhotos(albumId: Int): List<PhotoNetworkEntity>

    suspend fun getAlbums(): List<AlbumNetworkEntity>

    suspend fun getUsers(): List<UserNetworkEntity>
}