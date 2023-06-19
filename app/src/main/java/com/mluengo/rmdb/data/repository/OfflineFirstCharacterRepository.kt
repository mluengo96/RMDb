package com.mluengo.rmdb.data.repository

import com.mluengo.rmdb.data.datasource.LocalDataSource
import com.mluengo.rmdb.data.datasource.RemoteDataSource
import com.mluengo.rmdb.data.local.entitities.CharacterEntity
import com.mluengo.rmdb.data.local.entitities.asExternalModel
import com.mluengo.rmdb.data.model.Character
import com.mluengo.rmdb.data.network.models.NetworkCharacter
import com.mluengo.rmdb.data.network.models.asEntity
import com.mluengo.rmdb.data.network.models.asExternalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstCharacterRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
): CharacterRepository {

    // Remote retrieval of Characters
    override fun observeAllCharacters(): Flow<List<Character>> =
        flow {
            remoteDataSource
                .fetchCharacters()
                .body()?.let {
                    emit(
                        it.results.map(NetworkCharacter::asExternalModel)
                    )
                }
        }.flowOn(Dispatchers.IO)
    /*localDataSource.getCharacterEntities()
            .map { it.map(CharacterEntity::asExternalModel) }*/

    override fun observeCharactersById(id: Int): Flow<Character> {
        TODO("Not yet implemented")
    }

}