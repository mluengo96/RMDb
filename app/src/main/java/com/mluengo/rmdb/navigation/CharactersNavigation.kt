package com.mluengo.rmdb.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import com.mluengo.rmdb.data.model.Character
import com.mluengo.rmdb.ui.screens.characters.CharactersRoute

const val charactersNavigationRoute = "characters_navigation"

fun NavController.navigateToCharacters(navOptions: NavOptions? = null) {
    this.navigate(charactersNavigationRoute, navOptions)
}

fun NavGraphBuilder.charactersScreen(
    characters: LazyPagingItems<Character>,
    onNavigateToCharacter: () -> Unit,
) {
    composable(route = charactersNavigationRoute) {
        CharactersRoute(
            characters = characters,
            onNavigateToCharacter = onNavigateToCharacter
        )
    }
}