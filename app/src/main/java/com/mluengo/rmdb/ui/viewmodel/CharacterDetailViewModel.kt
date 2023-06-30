package com.mluengo.rmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mluengo.rmdb.data.model.Character
import com.mluengo.rmdb.data.network.NetworkResult
import com.mluengo.rmdb.data.network.asResult
import com.mluengo.rmdb.data.repository.OfflineFirstCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface CharacterUiState {
    data class Success(
        val character: Character
    ): CharacterUiState
    object Loading: CharacterUiState
    object Error: CharacterUiState
}

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterRepository: OfflineFirstCharacterRepository
): ViewModel() {

    val characterUiState: StateFlow<CharacterUiState> =
        characterUiState().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CharacterUiState.Loading,
        )

    private fun characterUiState(id: Int): Flow<CharacterUiState> {
        val characterStream: Flow<Character> = characterRepository.observeCharactersById(id)

        return characterStream.asResult()
            .map { characterResult ->
                when (characterResult) {
                    is NetworkResult.Success -> {
                        val character = characterResult.data
                        CharacterUiState.Success(character = character)
                    }

                    is NetworkResult.Loading -> {
                        CharacterUiState.Loading
                    }

                    is NetworkResult.Error -> {
                        CharacterUiState.Error
                    }
                }
            }
    }
}