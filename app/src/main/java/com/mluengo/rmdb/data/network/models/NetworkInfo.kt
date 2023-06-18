package com.mluengo.rmdb.data.network.models

import com.squareup.moshi.Json

data class NetworkInfo(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "pages") val pages: Int,
    @field:Json(name = "next") val next: String,
    @field:Json(name = "prev") val prev: String?
)
