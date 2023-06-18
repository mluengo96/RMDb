package com.mluengo.rmdb.data.network.models

import com.mluengo.rmdb.data.local.entitities.CharacterEntity

data class NetworkCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: NetworkLocation,
    val location: NetworkLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)

/**
 * Converts the network model to the local model for persisting
 * by the local data source
 */
fun NetworkCharacter.asEntity() = CharacterEntity(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.asEntity(),
    location = location.asEntity(),
    image = image,
    episode = episode,
    url = url,
    created = created,
)