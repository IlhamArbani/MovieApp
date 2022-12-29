package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.details.DetailSScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController();
    NavHost(navController = navController,
        startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }

        composable(MovieScreens.DetailScreen.name+"/{movie}"){
            backStackEntry ->
            DetailSScreen(navController = navController, backStackEntry.arguments?.getString("movie"))
        }
    }
}