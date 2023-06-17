package com.mluengo.rmdb.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import com.mluengo.rmdb.navigation.RmdbNavHost
import com.mluengo.rmdb.ui.components.RmdbNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RmdbApp(
    appState: RmdbAppState = rememberRmdbAppState(),
) {
    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            RmdbNavBar(
                destinations = appState.rmdbDestinations,
                currentDestination = appState.currentDestination,
                onNavigateToDestination = appState::navigateToDestination,
                modifier = Modifier.testTag("RMDbNavBar"),
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            //RmdbTopAppBar()
            RmdbNavHost(appState)
        }
    }
}