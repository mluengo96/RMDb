package com.mluengo.rmdb.data.local.entitities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mluengo.rmdb.data.model.Character

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationEntity,
    val location: LocationEntity,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)

/**
 * Converts the local model to the external model for use
 * by layers external to the data layer
 */
fun CharacterEntity.asExternalModel() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.asExternalModel(),
    location = location.asExternalModel(),
    image = image,
    episode = episode,
    url = url,
    created = created,
)