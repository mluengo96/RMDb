package com.mluengo.rmdb.data.datasource

import com.mluengo.rmdb.data.network.models.response.NetworkCharacterResponse
import com.mluengo.rmdb.data.network.retrofit.RetrofitApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteDataSource(
    private val api: RetrofitApi,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun fetchCharacters(): Response<NetworkCharacterResponse> =
        withContext(ioDispatcher) {
            api.getCharacters()
        }
}