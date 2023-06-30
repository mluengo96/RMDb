package com.mluengo.rmdb.data.repository

import androidx.paging.PagingData
import com.mluengo.rmdb.data.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun observeAllCharacters(): Flow<List<Character>>
    fun observeCharactersById(id: Int): Flow<Character>

    fun observePagingCharacters(): Flow<PagingData<Character>>
}