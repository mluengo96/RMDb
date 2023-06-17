package com.mluengo.rmdb.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mluengo.rmdb.ui.screens.locations.LocationsRoute

const val locationsNavigationRoute = "locations_navigation"

fun NavController.navigateToLocations(navOptions: NavOptions? = null) {
    this.navigate(locationsNavigationRoute, navOptions)
}

fun NavGraphBuilder.locationsScreen() {
    composable(route = locationsNavigationRoute) {
        LocationsRoute()
    }
}