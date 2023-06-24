package com.mluengo.rmdb.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.mluengo.rmdb.ui.screens.characters.CharacterScreen

@Composable
fun SkeletonLoader(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
) {
    if (isLoading) {
        Card(
            modifier = modifier,
            shape = CardDefaults.shape,
            colors = CardDefaults.cardColors(),
            elevation = CardDefaults.cardElevation(2.dp),
        ) {
            Column(
                modifier = modifier.padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .skeletonLoaderEffect()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .skeletonLoaderEffect()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.6f)
                        .height(15.dp)
                        .skeletonLoaderEffect()
                )
            }
        }
    } else {
        contentAfterLoading()
    }
}

fun Modifier.skeletonLoaderEffect() : Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000
            )
        )
    )
    val colors = listOf(
        Color.LightGray.copy(alpha = .9f),
        Color.LightGray.copy(alpha = .2f),
        Color.LightGray.copy(alpha = .9f)
    )

    background(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SkeletonLoaderPreview() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        items(10) {
            SkeletonLoader(
                isLoading = true
            ) {
                CharacterScreen()
            }
        }
    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SkeletonLoaderDarkPreview() {
    SkeletonLoader(
        isLoading = true
    ) {
        CharacterScreen()
    }
}