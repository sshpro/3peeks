package com.sshpro.threepeeks.business.network.mappers

import com.sshpro.threepeeks.business.Mapper
import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.network.data.AlbumNetworkEntity
import com.sshpro.threepeeks.business.network.data.NetworkEntity
import com.sshpro.threepeeks.business.network.data.PhotoNetworkEntity
import com.sshpro.threepeeks.business.network.data.UserNetworkEntity
import javax.inject.Inject

class NetworkAlbumMapper
@Inject
constructor() : Mapper<NetworkEntity, Album> {

    override fun mapToDomain(
        input: NetworkEntity
    ): Album {
        return Album(
            id = input.album.id,
            title = input.album.title,
            photoTitle = input.photo.title,
            thumbnailUrl = input.photo.thumbnailUrl,
            user = input.user.name
        )
    }
}