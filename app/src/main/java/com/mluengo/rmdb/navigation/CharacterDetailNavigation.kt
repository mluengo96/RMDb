package com.mluengo.rmdb.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mluengo.rmdb.ui.screens.characters.CharacterDetailRoute

const val characterDetailNavigationRoute = "character_detail_navigation"

fun NavController.navigateToCharacterDetail(navOptions: NavOptions? = null) {
    this.navigate(characterDetailNavigationRoute, navOptions)
}

fun NavGraphBuilder.characterDetailScreen() {
    composable(route = characterDetailNavigationRoute) {
        CharacterDetailRoute()
    }
}