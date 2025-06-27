package com.rahul.movieapp.navigation

enum class MovieScreen {
    HomeScreen,
    MovieDetailsScreen;

    companion object{
        fun fromRoute(route: String?): MovieScreen =
            when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                MovieDetailsScreen.name -> MovieDetailsScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}