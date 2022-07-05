package com.sshpro.threepeeks.business.network

import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("users")
    suspend fun get(): List<UserNetworkEntity>
}