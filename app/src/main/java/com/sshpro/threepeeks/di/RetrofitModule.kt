package com.sshpro.threepeeks.di

import com.squareup.moshi.Moshi
import com.sshpro.threepeeks.business.network.AlbumService
import com.sshpro.threepeeks.business.network.PhotoService
import com.sshpro.threepeeks.business.network.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

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
}