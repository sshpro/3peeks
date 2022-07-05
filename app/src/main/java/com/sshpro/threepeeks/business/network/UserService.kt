package com.sshpro.threepeeks.business.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("users")
    fun get(): Observable<List<UserNetworkEntity>>
}