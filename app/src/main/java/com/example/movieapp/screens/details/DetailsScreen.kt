package com.example.movieapp.screens.details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.model.getMovies

@Composable
fun DetailSScreen(navController: NavController, movieId: String?){
    val movie = getMovies().filter { movie -> movie.id == movieId }[0]
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.LightGray, elevation = 5.dp) {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back",
                modifier = Modifier.clickable {navController.popBackStack()})
                Spacer(modifier = Modifier.width(100.dp))
                Text(text = "Movies")
            }
        }
    }) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Row() {
                Surface(modifier = Modifier.width(124.dp)) {
                    AsyncImage(model = movie.images[0], contentDescription = "Poster Image")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(text = "${movie.title}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                            append("Plot: ")
                        }
                        withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp,
                            fontWeight = FontWeight.Light)
                        ){
                            append(movie.plot)
                        }
                    }, modifier = Modifier.padding(6.dp))
                    Divider(modifier = Modifier.padding(3.dp))
                    Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                    Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                    Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)
                    LazyRow{
                        items(movie.images){item ->
                            Log.d("posters", "$item")
                            Card(modifier = Modifier
                                .padding(12.dp)
                                .size(240.dp),
                                elevation = 5.dp) {
                                AsyncImage(model = item, contentDescription = "Posters")
                            }
                        }
                    }
                }
            }
        }
    }
}