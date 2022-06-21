package com.sshpro.threepeeks.business.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET

interface AlbumService {
    @GET("albums")
    fun get(): Observable<List<AlbumNetworkEntity>>
}