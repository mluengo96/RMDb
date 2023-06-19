package com.mluengo.rmdb.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mluengo.rmdb.data.local.database.dao.CharacterDao
import com.mluengo.rmdb.data.local.database.dao.EpisodeDao
import com.mluengo.rmdb.data.local.database.dao.LocationDao
import com.mluengo.rmdb.data.local.database.util.LocationConverter
import com.mluengo.rmdb.data.local.entitities.CharacterEntity
import com.mluengo.rmdb.data.local.entitities.EpisodeEntity
import com.mluengo.rmdb.data.local.entitities.LocationEntity

@Database(
    entities = [
        CharacterEntity::class,
        EpisodeEntity::class,
        LocationEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    LocationConverter::class
)
abstract class RmdbDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharacterDao
    abstract fun episodesDao(): EpisodeDao
    abstract fun locationsDao(): LocationDao
}