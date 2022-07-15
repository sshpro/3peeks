package com.sshpro.threepeeks.di

import com.sshpro.threepeeks.data.repository.AlbumRepository
import com.sshpro.threepeeks.data.repository.AlbumRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(
        networkPhotoMapper: AlbumRepositoryImpl
    ): AlbumRepository
}