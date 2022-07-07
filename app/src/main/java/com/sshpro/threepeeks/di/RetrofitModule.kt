package com.sshpro.threepeeks.di

import com.squareup.moshi.Moshi
import com.sshpro.threepeeks.data.remote.JSONPlaceholderApi
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
    fun provideApi(retrofit: Retrofit.Builder): JSONPlaceholderApi {
        return retrofit
            .build()
            .create(JSONPlaceholderApi::class.java)
    }

}