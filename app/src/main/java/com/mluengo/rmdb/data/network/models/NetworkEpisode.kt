package com.mluengo.rmdb.data.network.models

import com.mluengo.rmdb.data.local.entitities.EpisodeEntity

data class NetworkEpisode(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
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