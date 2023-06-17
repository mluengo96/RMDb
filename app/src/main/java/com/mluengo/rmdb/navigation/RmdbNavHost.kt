package com.mluengo.rmdb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mluengo.rmdb.ui.RmdbAppState

@Composable
fun RmdbNavHost(
    appState: RmdbAppState,
    startDestination: String = charactersNavigationRoute,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        charactersScreen()
        episodesScreen()
        locationsScreen()
    }
}