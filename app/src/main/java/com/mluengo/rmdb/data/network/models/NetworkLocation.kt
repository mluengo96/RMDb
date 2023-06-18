package com.mluengo.rmdb.data.network.models

import com.mluengo.rmdb.data.local.entitities.LocationEntity
import com.squareup.moshi.Json

data class NetworkLocation(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "dimension") val dimension: String,
    @field:Json(name = "residents") val residents: List<String>,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "created") val created: String,
)

/**
 * Converts the network model to the local model for persisting
 * by the local data source
 */
fun NetworkLocation.asEntity() = LocationEntity(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = residents,
    url = url,
    created = created,
)