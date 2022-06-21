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
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
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
    fun provideNetworkService(
        photoService: PhotoService,
        albumService: AlbumService
    ): NetworkService {
        return NetworkServiceImpl(photoService = photoService, albumService = albumService)
    }


    @Singleton
    @Provides
    fun provideNetworkMapper(): Mapper<AlbumNetworkEntity, PhotoNetworkEntity, Album> {
        return NetworkMapper()
    }
}