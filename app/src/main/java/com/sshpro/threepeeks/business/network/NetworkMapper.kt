package com.sshpro.threepeeks.business.network

import com.sshpro.threepeeks.business.Mapper
import com.sshpro.threepeeks.business.domain.Album
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : Mapper<AlbumNetworkEntity, PhotoNetworkEntity, Album> {
    override fun mapToDomain(album: AlbumNetworkEntity, photo: PhotoNetworkEntity): Album {
        return Album(
            id = album.id,
            title = album.title,
            userId = album.userId,
            photoTitle = photo.title,
            thumbnailUrl = photo.thumbnailUrl
        )
    }

    override fun mapToDomainList(
        albums: List<AlbumNetworkEntity>,
        photos: List<PhotoNetworkEntity>
    ): List<Album> {
        return albums.map { album ->
            mapToDomain(album, photos.find { photo ->
                photo.albumId == album.id
            } ?: PhotoNetworkEntity(0, 0, "", "", "")
            )
        }
    }

}