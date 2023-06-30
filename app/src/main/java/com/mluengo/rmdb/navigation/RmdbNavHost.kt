package com.mluengo.rmdb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.paging.compose.collectAsLazyPagingItems
import com.mluengo.rmdb.ui.RmdbAppState
import com.mluengo.rmdb.ui.viewmodel.CharacterViewModel

@Composable
fun RmdbNavHost(
    modifier: Modifier = Modifier,
    appState: RmdbAppState,
    startDestination: String = charactersNavigationRoute,
) {
    val navController = appState.navController
    val characterViewModel = hiltViewModel<CharacterViewModel>()
    val characters = characterViewModel.characterPagingFlow.collectAsLazyPagingItems()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        charactersScreen(
            characters = characters,
            onNavigateToCharacter = appState::navigateToCharacterDetail
        )
        characterDetailScreen(
            onBackClick = navController::navigateUp
        )
        episodesScreen()
        locationsScreen()
    }
}