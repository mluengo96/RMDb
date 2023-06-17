package com.mluengo.rmdb.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.mluengo.rmdb.navigation.RmdbDestination
import com.mluengo.rmdb.ui.icon.Icon.DrawableResourceIcon
import com.mluengo.rmdb.ui.icon.Icon.ImageVectorIcon

@Composable
fun RmdbNavBar(
    destinations: List<RmdbDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (RmdbDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isDestinationInHierarchy(destination)

            NavigationBarItem(
                selected =  selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) destination.selectedIcon
                    else destination.unselectedIcon

                    when (icon) {
                        is ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null
                        )

                        is DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null
                        )
                    }
                },
                label = { Text(text = stringResource(id = destination.iconTextId)) },
                modifier = Modifier,
            )
        }
    }
}

private fun NavDestination?.isDestinationInHierarchy(destination: RmdbDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
