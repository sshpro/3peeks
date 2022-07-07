package com.sshpro.threepeeks.business.network.data

data class NetworkEntity(
    val album: AlbumNetworkEntity,
    val photo: PhotoNetworkEntity,
    val user: UserNetworkEntity
)
