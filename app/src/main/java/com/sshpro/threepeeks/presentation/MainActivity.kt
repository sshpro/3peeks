package com.sshpro.threepeeks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.sshpro.threepeeks.presentation.album_list.AlbumView
import com.sshpro.threepeeks.presentation.photo_list.PhotosView
import com.sshpro.threepeeks.presentation.ui.theme.ThreePeeksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThreePeeksTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                val currentScreen = Screen.fromRoute(backStackEntry.value?.destination?.route)
                Scaffold(
                    topBar = { TopBarView(currentScreen = currentScreen) },
                ) { innerPadding ->
                    NavigationComponent(innerPadding = innerPadding, navController = navController)
                }
            }
        }
    }

    @Composable
    fun NavigationComponent(
        innerPadding: PaddingValues,
        navController: NavHostController
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Albums.name,
            modifier = Modifier.padding(innerPadding)) {
            composable(Screen.Albums.name) {
                AlbumView(navController)
            }
            composable(
                route = "${Screen.Photos.name}/{albumId}",
                arguments = listOf(
                    navArgument("albumId") {
                        type = NavType.IntType
                    }
                )
            ) {
                PhotosView()
            }
        }
    }
}

