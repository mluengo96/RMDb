package com.mluengo.rmdb.data.network.models

import com.mluengo.rmdb.data.local.entitities.EpisodeEntity
import com.squareup.moshi.Json

data class NetworkEpisode(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "airDate") val airDate: String,
    @field:Json(name = "episode") val episode: String,
    @field:Json(name = "characters") val characters: List<String>,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "created") val created: String,
)

/**
 * Converts the network model to the local model for persisting
 * by the local data source
 */
fun NetworkEpisode.asEntity() = EpisodeEntity(
    id = id,
    name = name,
    airDate = airDate,
    episode = episode,
    characters = characters,
    url = url,
    created = created,
)