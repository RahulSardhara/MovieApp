package com.rahul.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.rahul.movieapp.R
import com.rahul.movieapp.model.Movie
import com.rahul.movieapp.model.getMovies
import kotlinx.coroutines.delay


@Composable
@Preview
fun MovieRow(movieName: Movie = getMovies()[0], onItemClick: (Movie) -> Unit = {}) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onItemClick.invoke(movieName)
            }, shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp)
                    .background(color = Color.Transparent),
                shape = RectangleShape,
                tonalElevation = 6.dp,
                shadowElevation = 6.dp
            ) {
               /* ImageSwitcher(
                    images = movieName.images,
                    modifier = Modifier.fillMaxSize()
                )*/
                 AsyncImage(
                     model = ImageRequest.Builder(LocalContext.current)
                         .data(movieName.poster)
                         .crossfade(true)
                         .build(), contentDescription = "Movie Poster", placeholder = painterResource(id = R.drawable.ic_launcher_foreground), contentScale = ContentScale.Crop, modifier = Modifier
                         .fillMaxSize()
                         .clip(RoundedCornerShape(16.dp))
                         .background(color = MaterialTheme.colorScheme.secondary)
                 )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movieName.title, style = MaterialTheme.typography.bodyLarge, maxLines = 1)
                Text(text = "Director :${movieName.director}", style = MaterialTheme.typography.bodyMedium, maxLines = 1)
                Text(text = "Released :${movieName.year}", style = MaterialTheme.typography.bodyMedium, maxLines = 1)
                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Gray, fontSize = 13.sp)) {
                                append("Plot : ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Light)) {
                                append(movieName.plot)
                            }

                        }, modifier = Modifier.padding(start = 0.dp, top = 6.dp, bottom = 6.dp, end = 6.dp))
                        HorizontalDivider(modifier = Modifier.padding(start = 0.dp, top = 4.dp, bottom = 6.dp, end = 6.dp), thickness = DividerDefaults.Thickness, color = Color.LightGray)
                        Text(text = "Actors :${movieName.actors}", style = MaterialTheme.typography.bodyMedium, maxLines = 1)
                        Text(text = "Rating :${movieName.rating}", style = MaterialTheme.typography.bodyMedium, maxLines = 1)
                    }

                }
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expanded = !expanded },
                    tint = Color.Gray
                )


            }


        }
    }

}

@Composable
fun ImageSwitcher(images: List<String>, modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(images) {
        while (true) {
            delay(3000L)
            currentIndex = (currentIndex + 1) % images.size
        }
    }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(images[currentIndex])
            .crossfade(true)
            .build(),
        contentDescription = "Movie Poster",
        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.Transparent)
    )
}
