package com.mluengo.rmdb.di

import com.mluengo.rmdb.BuildConfig
import com.mluengo.rmdb.data.network.retrofit.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val RmdbBaseUrl: String = BuildConfig.BACKEND_URL

    @Provides
    @Singleton
    fun provideNetworkApi(
        okHttpCallFactory: Call.Factory
    ): RetrofitApi {
        return Retrofit.Builder()
            .baseUrl(RmdbBaseUrl)
            .callFactory(okHttpCallFactory)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .build()
}