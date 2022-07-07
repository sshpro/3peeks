package com.sshpro.threepeeks.data.remote

import com.sshpro.threepeeks.data.remote.dto.AlbumNetworkEntity
import com.sshpro.threepeeks.data.remote.dto.PhotoNetworkEntity
import com.sshpro.threepeeks.data.remote.dto.UserNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface JSONPlaceholderApi {
    @GET("albums")
    suspend fun getAlbums():List<AlbumNetworkEntity>

    @GET("photos")
    suspend fun getPhotos(): List<PhotoNetworkEntity>

    @GET("photos")
    suspend fun getPhotos(@Query("albumId") albumId: Int): List<PhotoNetworkEntity>

    @GET("users")
    suspend fun getUsers(): List<UserNetworkEntity>
}