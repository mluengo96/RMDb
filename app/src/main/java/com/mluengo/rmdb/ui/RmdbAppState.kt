package com.mluengo.rmdb.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.mluengo.rmdb.navigation.RmdbDestination
import com.mluengo.rmdb.navigation.RmdbDestination.CHARACTERS
import com.mluengo.rmdb.navigation.RmdbDestination.EPISODES
import com.mluengo.rmdb.navigation.RmdbDestination.LOCATIONS
import com.mluengo.rmdb.navigation.charactersNavigationRoute
import com.mluengo.rmdb.navigation.episodesNavigationRoute
import com.mluengo.rmdb.navigation.locationsNavigationRoute
import com.mluengo.rmdb.navigation.navigateToCharacters
import com.mluengo.rmdb.navigation.navigateToEpisodes
import com.mluengo.rmdb.navigation.navigateToLocations
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberRmdbAppState(
    //networkMonitor: NetworkMonitor,
    //userNewsResourceRepository: UserNewsResourceRepository,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): RmdbAppState {
    return remember(
        navController,
        coroutineScope,
        //networkMonitor,
        //userNewsResourceRepository,
    ) {
        RmdbAppState(
            navController,
            coroutineScope,
            //networkMonitor,
            //userNewsResourceRepository,
        )
    }
}

class RmdbAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    //networkMonitor: NetworkMonitor,
    //userNewsResourceRepository: UserNewsResourceRepository,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: RmdbDestination?
        @Composable get() = when (currentDestination?.route) {
            charactersNavigationRoute -> CHARACTERS
            episodesNavigationRoute -> EPISODES
            locationsNavigationRoute -> LOCATIONS
            else -> null
        }

    var shouldShowSettingsDialog by mutableStateOf(false)
        private set

    /*val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )*/

    /**
     * Map of RMDb destinations to be used in the BottomBar.
     */
    val rmdbDestinations: List<RmdbDestination> = RmdbDestination.values().asList()

    /**
     * UI logic for navigating to a destination in the app. RMDb destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param rmdbDestination: The destination the app needs to navigate to.
     */
    fun navigateToDestination(rmdbDestination: RmdbDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (rmdbDestination) {
            CHARACTERS -> navController.navigateToCharacters(topLevelNavOptions)
            EPISODES -> navController.navigateToEpisodes(topLevelNavOptions)
            LOCATIONS -> navController.navigateToLocations(topLevelNavOptions)
        }
    }

    fun setShowSettingsDialog(shouldShow: Boolean) {
        shouldShowSettingsDialog = shouldShow
    }

    /*fun navigateToSearch() {
        navController.navigateToSearch()
    }*/
}