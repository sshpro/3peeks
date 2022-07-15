package com.sshpro.threepeeks.di

import com.sshpro.threepeeks.data.Mapper
import com.sshpro.threepeeks.data.remote.dto.NetworkEntity
import com.sshpro.threepeeks.data.remote.dto.PhotoNetworkEntity
import com.sshpro.threepeeks.domain.mappers.remote.NetworkAlbumMapper
import com.sshpro.threepeeks.domain.mappers.remote.NetworkPhotoMapper
import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.domain.model.Photo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindNetworkAlbumMapper(
        networkAlbumMapper: NetworkAlbumMapper
    ): Mapper<NetworkEntity, Album>

    @Binds
    abstract fun bindNetworkPhotoMapper(
        networkPhotoMapper: NetworkPhotoMapper
    ): Mapper<PhotoNetworkEntity, Photo>
}