package com.mluengo.rmdb.data.network.models.response

import com.mluengo.rmdb.data.network.models.NetworkCharacter
import com.mluengo.rmdb.data.network.models.NetworkInfo
import com.squareup.moshi.Json

data class NetworkCharacterResponse(
    @field:Json(name = "info") val info: NetworkInfo,
    @field:Json(name = "results") val results: List<NetworkCharacter>
)
