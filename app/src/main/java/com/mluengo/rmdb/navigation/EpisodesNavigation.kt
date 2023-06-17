package com.mluengo.rmdb.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mluengo.rmdb.ui.screens.episodes.EpisodesRoute

const val episodesNavigationRoute = "episodes_navigation"

fun NavController.navigateToEpisodes(navOptions: NavOptions? = null) {
    this.navigate(episodesNavigationRoute, navOptions)
}

fun NavGraphBuilder.episodesScreen() {
    composable(route = episodesNavigationRoute) {
        EpisodesRoute()
    }
}