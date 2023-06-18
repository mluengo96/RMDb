package com.mluengo.rmdb.data.local.entitities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mluengo.rmdb.data.model.Location

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
)

/**
 * Converts the local model to the external model for use
 * by layers external to the data layer
 */
fun LocationEntity.asExternalModel() = Location(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = residents,
    url = url,
    created = created,
)