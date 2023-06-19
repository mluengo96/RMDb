package com.mluengo.rmdb.di

import com.mluengo.rmdb.data.local.database.RmdbDatabase
import com.mluengo.rmdb.data.local.database.dao.CharacterDao
import com.mluengo.rmdb.data.local.database.dao.EpisodeDao
import com.mluengo.rmdb.data.local.database.dao.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesCharactersDao(
        database: RmdbDatabase,
    ): CharacterDao = database.charactersDao()

    @Provides
    fun providesEpisodesDao(
        database: RmdbDatabase,
    ): EpisodeDao = database.episodesDao()

    @Provides
    fun providesLocationsDao(
        database: RmdbDatabase,
    ): LocationDao = database.locationsDao()
}