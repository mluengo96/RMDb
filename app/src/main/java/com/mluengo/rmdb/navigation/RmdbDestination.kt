package com.mluengo.rmdb.navigation

import com.mluengo.rmdb.R
import com.mluengo.rmdb.ui.icon.Icon
import com.mluengo.rmdb.ui.icon.Icon.DrawableResourceIcon
import com.mluengo.rmdb.ui.icon.RmdbIcons

enum class RmdbDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int,
    val route: String,
) {
    CHARACTERS(
        selectedIcon = DrawableResourceIcon(RmdbIcons.CharactersFilled),
        unselectedIcon = DrawableResourceIcon(RmdbIcons.CharactersBorder),
        iconTextId = R.string.characters,
        titleTextId = R.string.app_name,
        route = charactersNavigationRoute,
    ),
    EPISODES(
        selectedIcon = DrawableResourceIcon(RmdbIcons.EpisodesFilled),
        unselectedIcon = DrawableResourceIcon(RmdbIcons.EpisodesBorder),
        iconTextId = R.string.episodes,
        titleTextId = R.string.app_name,
        route = episodesNavigationRoute,
    ),
    LOCATIONS(
        selectedIcon = DrawableResourceIcon(RmdbIcons.LocationsFilled),
        unselectedIcon = DrawableResourceIcon(RmdbIcons.LocationsBorder),
        iconTextId = R.string.locations,
        titleTextId = R.string.app_name,
        route = locationsNavigationRoute,
    )
}