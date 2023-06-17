package com.mluengo.rmdb.ui.icon

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.mluengo.rmdb.R

object RmdbIcons {
    val CharactersFilled = R.drawable.groups_filled
    val CharactersBorder = R.drawable.groups_border

    val EpisodesFilled = R.drawable.theaters_filled
    val EpisodesBorder = R.drawable.theaters_border

    val LocationsFilled = R.drawable.pin_drop_filled
    val LocationsBorder = R.drawable.pin_drop_border
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}