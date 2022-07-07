package com.sshpro.threepeeks.business.network

import com.sshpro.threepeeks.business.network.data.AlbumNetworkEntity
import retrofit2.http.GET

interface AlbumService {
    @GET("albums")
    suspend fun get():List<AlbumNetworkEntity>
}