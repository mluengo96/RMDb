package com.mluengo.rmdb.ui.screens.characters

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mluengo.rmdb.R
import com.mluengo.rmdb.ui.icon.RmdbIcons
import com.mluengo.rmdb.ui.theme.spacingScheme

@Composable
internal fun CharacterDetailRoute(
    onBackClick: () -> Unit,
) {
    CharacterDetailScreen(onBackClick = onBackClick)
}

@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,

    ) {
        DetailToolbar(onBackClick = onBackClick)
        Title(modifier = modifier)
        Picture(modifier = modifier)
        Information(modifier = modifier)
        Episodes(modifier = modifier)
    }
}

@Composable
fun Picture(
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier.padding(
            vertical = MaterialTheme.spacingScheme.small,
            horizontal = MaterialTheme.spacingScheme.medium
        ),
    ) {
        val (image, text) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.mock_image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .constrainAs(image) { },
        )

        Surface(
            modifier = modifier
                .constrainAs(text) {
                    bottom.linkTo(image.bottom, margin = 16.dp)
                    start.linkTo(image.start, margin = 16.dp)
                },
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(MaterialTheme.spacingScheme.small)
            ) {
                Text(
                    text = "Alive",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier,
                )

                Spacer(
                    modifier = modifier.padding(MaterialTheme.spacingScheme.extraSmall)
                )

                Canvas(
                    modifier = modifier
                        .size(10.dp),
                    onDraw = {
                        when ("Alive") {
                            "Alive" -> drawCircle(Color.Green)
                            "Dead" -> drawCircle(Color.Red)
                            else -> drawCircle(Color.Gray)
                        }
                    }
                )
            }
        }

    }
}

@Composable
fun Title(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Rick Sanchez",
        style = MaterialTheme.typography.headlineLarge,
        maxLines = 1,
        modifier = modifier.padding(
            start = MaterialTheme.spacingScheme.medium,
            end = MaterialTheme.spacingScheme.medium,
            bottom = MaterialTheme.spacingScheme.medium
        ),
    )
}

@Composable
fun Information(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .padding(
                vertical = MaterialTheme.spacingScheme.small,
                horizontal = MaterialTheme.spacingScheme.medium
            )
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = modifier.padding(MaterialTheme.spacingScheme.small)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(MaterialTheme.spacingScheme.small)
            ) {
                Text(
                    text = "Species: ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = modifier,
                )

                Text(
                    text = "Human",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier,
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(MaterialTheme.spacingScheme.small)
            ) {
                Text(
                    text = "From: ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = modifier,
                )

                Text(
                    text = "Earth (C-137)",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier,
                )
            }
            Row(
                verticalAlignment = Alignment.Top,
                modifier = modifier.padding(MaterialTheme.spacingScheme.small)
            ) {
                Text(
                    text = "Last known location: ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = modifier,
                )

                Text(
                    text = "Citadel of Ricks",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier,
                )
            }
        }
    }
}

@Composable
fun Episodes(
    modifier: Modifier = Modifier,
) {
    Column {
        Text(
            modifier = modifier.padding(
                vertical = MaterialTheme.spacingScheme.small,
                horizontal = MaterialTheme.spacingScheme.medium
            ),
            text = "Appears in the following:",
            style = MaterialTheme.typography.headlineSmall
        )

        LazyRow(
            contentPadding = PaddingValues(MaterialTheme.spacingScheme.medium),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(5) { index ->
                EpisodeBox(index + 1)
            }
        }
    }
}

@Composable
fun EpisodeBox(
    index: Int,
) {
    Surface(
        modifier = Modifier,
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(MaterialTheme.spacingScheme.small)
        ) {
            Text(
                text = "Episode $index",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier,
            )

        }
    }
}

@Composable
private fun DetailToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = MaterialTheme.spacingScheme.medium),
    ) {
        IconButton(
            onClick = onBackClick,
        ) {
            Icon(
                imageVector = RmdbIcons.ArrowBack,
                contentDescription = stringResource(
                    id = R.string.back,
                ),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CharacterDetailPreview() {
    CharacterDetailScreen(
        onBackClick = { }
    )
}