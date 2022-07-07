package com.sshpro.threepeeks

import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.data.remote.dto.AlbumNetworkEntity
import com.sshpro.threepeeks.domain.mappers.remote.NetworkAlbumMapper
import com.sshpro.threepeeks.business.network.PhotoNetworkEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMapperTest {
    private val networkAlbumMapper = NetworkAlbumMapper()

    @Test
    fun shouldMapToAlbum() {
        val photoNetworkEntity = PhotoNetworkEntity(
            id = 1,
            albumId = 1,
            title = "PhotoTitle",
            url = "url",
            thumbnailUrl = "thumb"
        )
        val albumEntity = AlbumNetworkEntity(
            id = 1,
            userId = 1,
            title = "title",
        )

        val album = networkAlbumMapper.mapToDomain(albumEntity, photoNetworkEntity)
        assertEquals(
            album, Album(
                id = 1,
                title = "title",
                userId = 1,
                photoTitle = "PhotoTitle",
                thumbnailUrl = "thumb"
            )
        )
    }
}