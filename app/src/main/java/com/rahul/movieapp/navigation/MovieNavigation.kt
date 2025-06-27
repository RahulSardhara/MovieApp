package com.rahul.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rahul.movieapp.screens.home.HomeScreen
import com.rahul.movieapp.screens.movie_details_screen.MovieDetailScreen

@Composable
fun MovieNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreen.HomeScreen.name) {

        composable(MovieScreen.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(route = MovieScreen.MovieDetailsScreen.name) {
            MovieDetailScreen(navController = navController)
        }


    }
}
