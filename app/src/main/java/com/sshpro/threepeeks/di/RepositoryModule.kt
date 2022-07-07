package com.sshpro.threepeeks.di

import com.sshpro.threepeeks.business.AlbumRepository
import com.sshpro.threepeeks.business.Mapper
import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.domain.Photo
import com.sshpro.threepeeks.business.network.NetworkService
import com.sshpro.threepeeks.business.network.data.AlbumNetworkEntity
import com.sshpro.threepeeks.business.network.data.NetworkEntity
import com.sshpro.threepeeks.business.network.data.PhotoNetworkEntity
import com.sshpro.threepeeks.business.network.data.UserNetworkEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(
        networkService: NetworkService,
        networkAlbumMapper: Mapper<NetworkEntity, Album>,
        networkPhotoMapper: Mapper<PhotoNetworkEntity, Photo>,
        coroutineDispatcher: CoroutineDispatcher
    ): AlbumRepository {
        return AlbumRepository(
            networkService = networkService,
            networkAlbumMapper = networkAlbumMapper,
            networkPhotoMapper = networkPhotoMapper,
            coroutineDispatcher = coroutineDispatcher
        )
    }
}