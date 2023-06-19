package com.mluengo.rmdb.data.network.models

import com.mluengo.rmdb.data.local.entitities.CharacterEntity
import com.mluengo.rmdb.data.model.Character
import com.mluengo.rmdb.data.model.CharacterLocation
import com.squareup.moshi.Json

data class NetworkCharacter(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "status") val status: String,
    @field:Json(name = "species") val species: String,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "gender") val gender: String,
    @field:Json(name = "origin") val origin: CharacterLocation,
    @field:Json(name = "location") val location: CharacterLocation,
    @field:Json(name = "image") val image: String,
    @field:Json(name = "episode") val episode: List<String>,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "created") val created: String,
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
    origin = origin,
    location = location,
    image = image,
    episode = episode,
    url = url,
    created = created,
)

fun NetworkCharacter.asExternalModel() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin,
    location = location,
    image = image,
    episode = episode,
    url = url,
    created = created,
)