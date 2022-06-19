package com.sshpro.threepeeks.business.network

import com.sshpro.threepeeks.business.Mapper
import com.sshpro.threepeeks.business.domain.Album
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : Mapper<AlbumNetworkEntity, PhotoNetworkEntity, Album> {
    override fun mapToDomain(input1: AlbumNetworkEntity, input2: PhotoNetworkEntity): Album {
        return Album(
            id = input1.id,
            title = input1.title,
            userId = input1.userId,
            photoTitle = input2.title,
            thumbnailUrl = input2.thumbnailUrl
        )
    }
}