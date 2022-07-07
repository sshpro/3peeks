package com.sshpro.threepeeks.business.network.mappers

import com.sshpro.threepeeks.business.Mapper
import com.sshpro.threepeeks.business.domain.Photo
import com.sshpro.threepeeks.business.network.data.PhotoNetworkEntity
import javax.inject.Inject

class NetworkPhotoMapper
@Inject
constructor() : Mapper<PhotoNetworkEntity, Photo> {

    override fun mapToDomain(
        input: PhotoNetworkEntity
    ): Photo {
        return Photo(
            id = input.id,
            albumId = input.albumId,
            title = input.title,
            url = input.url
        )
    }
}