package com.sshpro.threepeeks

import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.network.AlbumNetworkEntity
import com.sshpro.threepeeks.business.network.NetworkMapper
import com.sshpro.threepeeks.business.network.PhotoNetworkEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMapperTest {
    private val networkMapper = NetworkMapper()

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

        val album = networkMapper.mapToDomain(albumEntity, photoNetworkEntity)
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