package com.mluengo.rmdb.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.mluengo.rmdb.data.local.entitities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE id = :idCharacter")
    fun getSingleCharacter(idCharacter: Int): Flow<CharacterEntity>

    /*fun getMultipleCharacters()

    fun insertCharacters()*/
}