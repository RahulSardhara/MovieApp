package com.rahul.movieapp.screens.movie_details_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MovieDetailScreen(navController: NavController, movieName: String) {
    Text("Movie Details Screen $movieName")

}