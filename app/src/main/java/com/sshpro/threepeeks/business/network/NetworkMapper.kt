package com.sshpro.threepeeks.business.network

import com.sshpro.threepeeks.business.Mapper
import com.sshpro.threepeeks.business.domain.Album
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : Mapper<AlbumNetworkEntity, PhotoNetworkEntity, UserNetworkEntity, Album> {
    override fun mapToDomain(input1: AlbumNetworkEntity,
                             input2: PhotoNetworkEntity,
                             input3: UserNetworkEntity): Album {
        return Album(
            id = input1.id,
            title = input1.title,
            user = input3.name,
            photoTitle = input2.title,
            thumbnailUrl = input2.thumbnailUrl
        )
    }
}