package com.mluengo.rmdb.ui.screens.characters

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mluengo.rmdb.data.model.Character
import com.mluengo.rmdb.ui.components.SkeletonLoader
import com.mluengo.rmdb.ui.theme.spacingScheme
import com.mluengo.rmdb.ui.viewmodel.CharacterViewModel
import com.mluengo.rmdb.ui.viewmodel.CharactersUiState
import com.mluengo.rmdb.ui.viewmodel.CharactersUiState.Loading
import com.mluengo.rmdb.ui.viewmodel.CharactersUiState.Success

@Composable
internal fun CharactersRoute(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = hiltViewModel(),
    onNavigateToCharacter: () -> Unit,
) {
    val charactersUiState: CharactersUiState by viewModel.characterUiState.collectAsStateWithLifecycle()

    CharacterScreen(
        charactersUiState = charactersUiState,
        onNavigateToCharacter = onNavigateToCharacter,
    )
}

@Composable
internal fun CharacterScreen(
    modifier: Modifier = Modifier,
    charactersUiState: CharactersUiState? = null,
    onNavigateToCharacter: () -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacingScheme.medium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacingScheme.medium),
        modifier = modifier
            .padding(horizontal = MaterialTheme.spacingScheme.medium)
            .fillMaxSize()
    ) {

        when (charactersUiState) {
            is Success -> {
                items(charactersUiState.characters, key = { it.id }) { characterResource ->
                    CharacterCard(
                        character = characterResource,
                        modifier = Modifier.wrapContentSize(),
                        onNavigateToCharacter = onNavigateToCharacter,
                    )
                }
            }

            Loading -> {
                items(10) {
                    SkeletonLoader(
                        isLoading = charactersUiState is Loading,
                    ) {

                    }
                }
            }

            else -> Unit
        }
    }
}

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: Character,
    onNavigateToCharacter: () -> Unit,
) {
    val context = LocalContext.current
    OutlinedCard(
        modifier = modifier.clickable { onNavigateToCharacter() },
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.outlinedCardColors(),
        elevation = CardDefaults.outlinedCardElevation(),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier.padding(MaterialTheme.spacingScheme.medium)
        ) {
            val imageUrl = character.image
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .memoryCacheKey(imageUrl)
                    .diskCacheKey(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp))
                    .requiredSize(128.dp),
            )

            Text(
                text = character.name,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
                modifier = modifier.padding(vertical = MaterialTheme.spacingScheme.small),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(
                    modifier = Modifier
                        .size(10.dp),
                    onDraw = {
                        when (character.status) {
                            "Alive" -> drawCircle(Color.Green)
                            "Dead" -> drawCircle(Color.Red)
                            else -> drawCircle(Color.Gray)
                        }
                    }
                )

                Text(
                    text = character.status,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = modifier.padding(start = 4.dp),
                )

                Text(
                    text = "-",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = modifier.padding(horizontal = 4.dp),
                )

                Text(
                    text = character.species,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = modifier,
                )
            }
        }
    }
}