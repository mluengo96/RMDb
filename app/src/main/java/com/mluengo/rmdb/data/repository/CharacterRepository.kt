package com.mluengo.rmdb.data.repository

import com.mluengo.rmdb.data.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun observeAllCharacters(): Flow<List<Character>>
    fun observeCharactersById(id: Int): Flow<Character>
}