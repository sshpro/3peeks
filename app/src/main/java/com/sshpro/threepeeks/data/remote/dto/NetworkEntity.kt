package com.sshpro.threepeeks.data.remote.dto

data class NetworkEntity(
    val album: AlbumNetworkEntity,
    val photo: PhotoNetworkEntity,
    val user: UserNetworkEntity
)
