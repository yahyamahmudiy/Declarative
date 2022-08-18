package com.example.declarative.activity.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.declarative.item.itemTVShow
import com.example.declarative.model.TVShow
import com.example.declarative.viewmodel.MainViewModel

@Composable
fun MainScreen(navController: NavController) {
    val viewModel = hiltViewModel<MainViewModel>()  /*,viewModel: MainViewModel = hiltViewModel()*/
    val isLoading by viewModel.isLoading.observeAsState(false)
    val tvShows by viewModel.tvShowsFromApi.observeAsState(arrayListOf())

    MainScreenContent(onItemClick = {
        // Save TV Show locally
        viewModel.insertTVShowToDB(it)
        //Open DetailsScreen
        navController.navigate("details" + "/${it.id}" + "/${it.name}" + "/${it.network}")

    }, isLoading = isLoading, tvShows = tvShows)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenContent(onItemClick: ((TVShow) -> Unit?)?, isLoading: Boolean, tvShows: List<TVShow>) {
    Scaffold(
        backgroundColor = Color.Black,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                title = { Text(text = "TV Show", color = Color.White) },
            )
        }
    ) {
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            LazyVerticalGrid(cells = GridCells.Fixed(2), modifier = Modifier.padding(5.dp)) {
                items(tvShows.size) {
                    val tvShow = tvShows[it]
                    itemTVShow(onItemClick!!, tvShow)
                }
            }
        }
    }
}




