package com.sshpro.threepeeks.business.network

import javax.inject.Inject

class NetworkServiceImpl @Inject constructor(
    private val albumService: AlbumService,
    private val photoService: PhotoService,
    private val userService: UserService
) : NetworkService {
    override fun getPhotos() = photoService.get()
    override fun getPhotos(albumId: Int) = photoService.get(albumId)

    override fun getAlbums() = albumService.get()
    override fun getUsers() = userService.get()
}