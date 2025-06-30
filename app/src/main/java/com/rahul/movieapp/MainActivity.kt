package com.rahul.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.rahul.movieapp.model.Movie
import com.rahul.movieapp.model.getMovies
import com.rahul.movieapp.navigation.MovieNavigation
import com.rahul.movieapp.navigation.MovieScreen
import com.rahul.movieapp.ui.theme.MovieAppTheme
import com.rahul.movieapp.widgets.MovieRow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(content: @Composable () -> Unit) {
    MovieAppTheme {
        content()
    }

}

@Composable
fun MainContent(paddingValues: PaddingValues, navController: NavController, movieList: List<Movie> = getMovies()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(movieName = it) { it ->
                    // Handle item click here
                    navController.navigate(route = MovieScreen.MovieDetailsScreen.name + "/${it.id}")
                    println("Clicked on movie: $it")
                }
            }
        }

    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp {
        MovieNavigation()
    }
}