package com.sshpro.threepeeks.business

import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.network.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function3
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val networkService: NetworkService,
    private val networkMapper: NetworkMapper,
) {
    val albums: Observable<List<Album>>
        get() {
            return Observable.zip(
                networkService.getAlbums().subscribeOn(Schedulers.io()),
                networkService.getPhotos().subscribeOn(Schedulers.io()),
                networkService.getUsers().subscribeOn(Schedulers.io()),
                zipper()
            ).observeOn(AndroidSchedulers.mainThread())
        }

    private fun zipper(): Function3<List<AlbumNetworkEntity>,
            List<PhotoNetworkEntity>,
            List<UserNetworkEntity>,
            List<Album>> = Function3 { albums, photos, users ->
        albums.map { album ->
            val foundPhoto =
                photos.find { photo -> photo.albumId == album.id } ?: defaultPhoto()
            val foundUser = users.find { user -> user.id == album.userId } ?: defaultUser()
            networkMapper.mapToDomain(album, foundPhoto, foundUser)
        }
    }

}

