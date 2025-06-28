package com.rahul.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rahul.movieapp.screens.home.HomeScreen
import com.rahul.movieapp.screens.movie_details_screen.MovieDetailScreen

@Composable
fun MovieNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreen.HomeScreen.name) {

        composable(MovieScreen.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(route = MovieScreen.MovieDetailsScreen.name+"/{movieName}", arguments = listOf(
            navArgument("movieName") {
                defaultValue = "Unknown Movie"
                type = androidx.navigation.NavType.StringType
            }
        )) {
            it.arguments?.getString("movieName")?.let { movieName ->
                println("Navigated to Movie Details Screen for: $movieName")
                MovieDetailScreen(navController = navController,movieName)

            }
        }


    }
}
