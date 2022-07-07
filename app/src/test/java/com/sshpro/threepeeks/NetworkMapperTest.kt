package com.sshpro.threepeeks

import com.sshpro.threepeeks.data.remote.dto.PhotoNetworkEntity
import com.sshpro.threepeeks.domain.mappers.remote.NetworkPhotoMapper
import com.sshpro.threepeeks.domain.model.Photo
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMapperTest {
    private val networkPhotoMapper = NetworkPhotoMapper()

    @Test
    fun shouldMapToAlbum() {
        val photoNetworkEntity = PhotoNetworkEntity(
            id = 1,
            albumId = 1,
            title = "PhotoTitle",
            url = "url",
            thumbnailUrl = "thumb"
        )


        val photo = networkPhotoMapper.mapToDomain(photoNetworkEntity)
        assertEquals(
            photo, Photo(
                id = 1,
                title = "PhotoTitle",
                albumId = 1,
                url = "url"
            )
        )
    }
}