package com.sshpro.threepeeks.business.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET("photos")
    fun get(): Observable<List<PhotoNetworkEntity>>

    @GET("photos")
    fun get(
        @Query("albumId") albumId: Int
    ): Observable<List<PhotoNetworkEntity>>
}