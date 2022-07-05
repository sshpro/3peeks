package com.sshpro.threepeeks.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.sshpro.threepeeks.business.Mapper
import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private fun baseUrl(): HttpUrl = "https://jsonplaceholder.typicode.com/".toHttpUrl()

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Singleton
    @Provides
    fun providePhotoService(retrofit: Retrofit.Builder): PhotoService {
        return retrofit
            .build()
            .create(PhotoService::class.java)
    }

    @Singleton
    @Provides
    fun provideAlbumService(retrofit: Retrofit.Builder): AlbumService {
        return retrofit
            .build()
            .create(AlbumService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit.Builder): UserService {
        return retrofit
            .build()
            .create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkService(
        photoService: PhotoService,
        albumService: AlbumService,
        userService: UserService,
    ): NetworkService {
        return NetworkServiceImpl(photoService = photoService,
            albumService = albumService,
            userService = userService)
    }


    @Singleton
    @Provides
    fun provideNetworkMapper(): Mapper<AlbumNetworkEntity, PhotoNetworkEntity, UserNetworkEntity, Album> {
        return NetworkMapper()
    }
}