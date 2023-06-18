package com.mluengo.rmdb.data.network.models

import com.mluengo.rmdb.data.local.entitities.LocationEntity

data class NetworkLocation(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
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