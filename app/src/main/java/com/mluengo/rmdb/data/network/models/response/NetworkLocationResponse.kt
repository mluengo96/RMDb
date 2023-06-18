package com.mluengo.rmdb.data.network.models.response

import com.mluengo.rmdb.data.network.models.NetworkInfo
import com.mluengo.rmdb.data.network.models.NetworkLocation
import com.squareup.moshi.Json

data class NetworkLocationResponse(
    @field:Json(name = "info") val info: NetworkInfo,
    @field:Json(name = "results") val results: List<NetworkLocation>
)
