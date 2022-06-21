package com.sshpro.threepeeks.business.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call

interface NetworkService {
     fun getPhotos(): Observable<List<PhotoNetworkEntity>>
     fun getPhotos(albumId: Int): Observable<List<PhotoNetworkEntity>>
     fun getAlbums(): Observable<List<AlbumNetworkEntity>>
}