package com.sshpro.threepeeks.domain.mappers.remote

import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.data.remote.dto.NetworkEntity
import com.sshpro.threepeeks.data.Mapper
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