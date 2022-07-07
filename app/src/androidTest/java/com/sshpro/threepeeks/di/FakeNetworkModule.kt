package com.sshpro.threepeeks.di

import com.squareup.moshi.Moshi
import com.sshpro.threepeeks.business.Mapper
import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.network.*
import com.sshpro.threepeeks.business.network.data.AlbumNetworkEntity
import com.sshpro.threepeeks.business.network.mappers.NetworkAlbumMapper
import com.sshpro.threepeeks.business.network.AlbumService
import com.sshpro.threepeeks.business.network.NetworkService
import com.sshpro.threepeeks.business.network.PhotoService
import com.sshpro.threepeeks.network.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RetrofitModule::class]
)
class FakeNetworkModule {

    @Singleton
    @Provides
    fun providesFakeMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideFakeRetrofit(moshi: Moshi): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Singleton
    @Provides
    fun provideFakeAlbumService(retrofit: Retrofit.Builder): AlbumService {
        return retrofit
            .build()
            .create(AlbumService::class.java)
    }

    @Singleton
    @Provides
    fun provideFakePhotoService(retrofit: Retrofit.Builder): PhotoService {
        return retrofit
            .build()
            .create(PhotoService::class.java)
    }

    @Singleton
    @Provides
    fun provideFakeNetworkService(
        albumService: AlbumService,
        photoService: PhotoService
    ): NetworkService {
        return NetworkServiceImpl(albumService = albumService, photoService = photoService)
    }

    @Singleton
    @Provides
    fun provideFakeNetworkMapper(): Mapper<AlbumNetworkEntity, PhotoNetworkEntity, Album> {
        return NetworkAlbumMapper()
    }
}