package com.mluengo.rmdb.data.network.retrofit

import com.mluengo.rmdb.data.network.models.response.NetworkCharacterResponse
import com.mluengo.rmdb.data.network.models.response.NetworkEpisodeResponse
import com.mluengo.rmdb.data.network.models.response.NetworkLocationResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {
    @GET("character")
    suspend fun getCharacters(): Response<NetworkCharacterResponse>

    @GET("episode")
    suspend fun getEpisodes(): Response<NetworkEpisodeResponse>

    @GET("location")
    suspend fun getLocations(): Response<NetworkLocationResponse>
}