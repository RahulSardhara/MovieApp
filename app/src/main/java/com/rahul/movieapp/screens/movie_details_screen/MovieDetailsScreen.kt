package com.rahul.movieapp.screens.movie_details_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.rahul.movieapp.model.Movie
import com.rahul.movieapp.model.getMovies
import com.rahul.movieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(navController: NavController, movieName: String) {

    val movie : Movie? = getMovies().firstOrNull { it.id == movieName }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(movie?.title?:"") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                movie?.let {
                    MovieRow(movieName = movie)
                    Text(it.title, style = MaterialTheme.typography.headlineLarge)
                    HorizontalScrollableImageView(it)
                }


            }
        }


    }

}

@Composable
private fun HorizontalScrollableImageView(movie1: Movie) {
    LazyRow {
        items(movie1.images) { image ->
            Card(modifier = Modifier
                .padding(12.dp)
                .size(240.dp), elevation = CardDefaults.cardElevation(5.dp)) {
                AsyncImage(
                    model = image,
                    contentDescription = "Movie Image",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(240.dp)
                        .clip(MaterialTheme.shapes.large),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}