package com.sshpro.threepeeks.di

import com.sshpro.threepeeks.business.AlbumRepository
import com.sshpro.threepeeks.business.network.NetworkMapper
import com.sshpro.threepeeks.business.network.NetworkService
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
        networkMapper: NetworkMapper,
    ): AlbumRepository {
        return AlbumRepository(
            networkService = networkService,
            networkMapper = networkMapper,
        )
    }
}