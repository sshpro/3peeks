package com.sshpro.threepeeks.business.network

import com.sshpro.threepeeks.business.network.data.UserNetworkEntity
import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun get(): List<UserNetworkEntity>
}