package com.sshpro.threepeeks.di

import com.squareup.moshi.Moshi
import com.sshpro.threepeeks.data.remote.JSONPlaceholderApi
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
    fun provideApi(retrofit: Retrofit.Builder): JSONPlaceholderApi {
        return retrofit
            .build()
            .create(JSONPlaceholderApi::class.java)
    }
}