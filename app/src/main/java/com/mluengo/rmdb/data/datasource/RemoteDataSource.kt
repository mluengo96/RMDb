package com.mluengo.rmdb.data.datasource

import com.mluengo.rmdb.data.network.Dispatcher
import com.mluengo.rmdb.data.network.RmdbDispatchers
import com.mluengo.rmdb.data.network.models.response.NetworkCharacterResponse
import com.mluengo.rmdb.data.network.retrofit.RetrofitApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: RetrofitApi,
    @Dispatcher(RmdbDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun fetchCharacters(): Response<NetworkCharacterResponse> =
        withContext(ioDispatcher) {
            api.getCharacters()
        }
}