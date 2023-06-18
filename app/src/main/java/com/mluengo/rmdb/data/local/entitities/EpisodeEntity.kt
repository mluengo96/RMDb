package com.mluengo.rmdb.data.local.entitities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mluengo.rmdb.data.model.Episode

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "air_date")
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
)

/**
 * Converts the local model to the external model for use
 * by layers external to the data layer
 */
fun EpisodeEntity.asExternalModel() = Episode(
    id = id,
    name = name,
    airDate = airDate,
    episode = episode,
    characters = characters,
    url = url,
    created = created,
)