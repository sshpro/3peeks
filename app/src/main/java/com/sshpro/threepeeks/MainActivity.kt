package com.sshpro.threepeeks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sshpro.threepeeks.business.DataState
import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.domain.Photo
import com.sshpro.threepeeks.compose.albums.AlbumView
import com.sshpro.threepeeks.compose.TopBarView
import com.sshpro.threepeeks.compose.collectAsStateLifecycleAware
import com.sshpro.threepeeks.compose.photos.PhotosView
import com.sshpro.threepeeks.ui.theme.ThreePeeksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThreePeeksTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                val currentScreen = Screens.fromRoute(backStackEntry.value?.destination?.route)
                Scaffold(
                    topBar = { TopBarView(currentScreen = currentScreen) },
                ) { innerPadding ->
                    NavigationComponent(innerPadding = innerPadding, navController = navController)
                }
            }
        }
        viewModel.getAlbums()
    }

    @Composable
    fun NavigationComponent(
        innerPadding: PaddingValues,
        navController: NavHostController
    ) {
        NavHost(
            navController = navController,
            startDestination = Screens.Albums.name,
            modifier = Modifier.padding(innerPadding)) {
            composable(Screens.Albums.name) {
                AlbumView(dataState = albumState(), onAlbumClick = { albumId ->
                    navController.navigate("${Screens.Photos.name}/$albumId")
                })
            }
            composable(
                route = "${Screens.Photos.name}/{albumId}",
                arguments = listOf(
                    navArgument("albumId") {
                        type = NavType.IntType
                    }
                )
            ) { entry ->
                val albumId = entry.arguments?.getInt("albumId") ?: 0
                viewModel.getPhotos(albumId)
                PhotosView(dataState = photoState())
            }
        }
    }

    @Composable
    private fun albumState(): DataState<List<Album>> {
        return viewModel.albums
            .collectAsStateLifecycleAware(initial = DataState.Loading)
            .value
    }

    @Composable
    private fun photoState(): DataState<List<Photo>> {
        return viewModel.photos
            .collectAsStateLifecycleAware(initial = DataState.Loading)
            .value
    }
}

