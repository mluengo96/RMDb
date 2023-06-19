package com.mluengo.rmdb.data.datasource

import com.mluengo.rmdb.data.local.database.dao.CharacterDao
import com.mluengo.rmdb.data.local.database.dao.EpisodeDao
import com.mluengo.rmdb.data.local.database.dao.LocationDao
import com.mluengo.rmdb.data.local.entitities.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val characterDao: CharacterDao,
    private val episodeDao: EpisodeDao,
    private val locationDao: LocationDao,
) {
    fun getCharacterEntities(): Flow<List<CharacterEntity>> =
        characterDao.getAllCharacters()
}