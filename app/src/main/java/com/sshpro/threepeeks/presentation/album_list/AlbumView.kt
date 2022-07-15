package com.sshpro.threepeeks.presentation.album_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sshpro.threepeeks.R
import com.sshpro.threepeeks.presentation.ErrorView
import com.sshpro.threepeeks.presentation.ProgressView
import com.sshpro.threepeeks.presentation.Screen
import com.sshpro.threepeeks.presentation.UiState
import com.sshpro.threepeeks.presentation.album_list.model.AlbumUiEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumView(
    navController: NavController,
    viewModel: AlbumListViewModel = hiltViewModel(),
) {
    when (val state = viewModel.state.value) {
        is UiState.Success<List<AlbumUiEntity>> -> {
            AlbumListView(items = state.data) { albumId ->
                navController.navigate("${Screen.Photos.name}/$albumId")
            }
        }
        is UiState.Error -> {
            ErrorView(state.message ?: stringResource(id = R.string.default_error))
        }
        is UiState.Loading -> {
            ProgressView()
        }
    }
}
