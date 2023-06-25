package com.mluengo.rmdb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mluengo.rmdb.ui.RmdbAppState

@Composable
fun RmdbNavHost(
    modifier: Modifier = Modifier,
    appState: RmdbAppState,
    startDestination: String = charactersNavigationRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        charactersScreen(onNavigateToCharacter = appState::navigateToCharacterDetail)
        characterDetailScreen()
        episodesScreen()
        locationsScreen()
    }
}