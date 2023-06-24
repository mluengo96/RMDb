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
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface CharactersUiState {
    data class Success(
        val characters: List<Character>
    ): CharactersUiState
    object Loading: CharactersUiState
    object Error: CharactersUiState
}

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: OfflineFirstCharacterRepository
): ViewModel() {

    val characterUiState: StateFlow<CharactersUiState> =
        charactersUiState().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CharactersUiState.Loading,
        )

    private fun charactersUiState(): Flow<CharactersUiState> {
        val charactersStream: Flow<List<Character>> = characterRepository.observeAllCharacters()

        return charactersStream.asResult()
            .map { charactersResult ->
                when (charactersResult) {
                    is NetworkResult.Success -> {
                        val characters = charactersResult.data
                        CharactersUiState.Success(characters = characters)
                    }

                    is NetworkResult.Loading -> {
                        CharactersUiState.Loading
                    }

                    is NetworkResult.Error -> {
                        CharactersUiState.Error
                    }
                }
            }
    }
}