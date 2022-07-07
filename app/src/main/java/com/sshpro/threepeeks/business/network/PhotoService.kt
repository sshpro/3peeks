package com.sshpro.threepeeks.business.network

import com.sshpro.threepeeks.business.network.data.PhotoNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET("photos")
    suspend fun get(): List<PhotoNetworkEntity>

    @GET("photos")
    suspend fun get(
        @Query("albumId") albumId: Int
    ): List<PhotoNetworkEntity>
}