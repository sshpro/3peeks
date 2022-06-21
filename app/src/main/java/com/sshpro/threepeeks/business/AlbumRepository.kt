package com.sshpro.threepeeks.business

import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.network.NetworkMapper
import com.sshpro.threepeeks.business.network.NetworkService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val networkService: NetworkService,
    private val networkMapper: NetworkMapper,
) {
    val albums: Single<List<Album>>
        get() {
            return networkService
                .getAlbums()
                .flatMap { albumEntities -> Observable.fromIterable(albumEntities) }
                .flatMap { albumEntity ->
                    networkService.getPhotos(albumEntity.id).flatMap { photoEntities ->
                        val photoNetworkEntity = photoEntities.first()
                        val album = networkMapper.mapToDomain(albumEntity, photoNetworkEntity)
                        Observable.just(album)
                    }
                }.toSortedList(compareBy{it.id})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
}

