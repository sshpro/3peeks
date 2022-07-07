package com.sshpro.threepeeks.di

import com.sshpro.threepeeks.business.Mapper
import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.domain.Photo
import com.sshpro.threepeeks.business.network.NetworkService
import com.sshpro.threepeeks.business.network.NetworkServiceImpl
import com.sshpro.threepeeks.business.network.data.NetworkEntity
import com.sshpro.threepeeks.business.network.data.PhotoNetworkEntity
import com.sshpro.threepeeks.business.network.mappers.NetworkAlbumMapper
import com.sshpro.threepeeks.business.network.mappers.NetworkPhotoMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindNetworkService(
        networkServiceImpl: NetworkServiceImpl
    ): NetworkService

    @Binds
    abstract fun bindNetworkAlbumMapper(
        networkAlbumMapper: NetworkAlbumMapper
    ): Mapper<NetworkEntity, Album>

    @Binds
    abstract fun bindNetworkPhotoMapper(
        networkPhotoMapper: NetworkPhotoMapper
    ): Mapper<PhotoNetworkEntity, Photo>
}