package com.sshpro.threepeeks.business.network

import javax.inject.Inject

class NetworkServiceImpl @Inject constructor(
    private val albumService: AlbumService,
    private val photoService: PhotoService,
    private val userService: UserService
) : NetworkService {
    override suspend fun getPhotos() = photoService.get()
    override suspend fun getPhotos(albumId: Int) = photoService.get(albumId)

    override suspend fun getAlbums() = albumService.get()
    override suspend fun getUsers() = userService.get()
}