package com.sshpro.threepeeks.presentation.photo_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.sshpro.threepeeks.R
import com.sshpro.threepeeks.presentation.ErrorView
import com.sshpro.threepeeks.presentation.ProgressView
import com.sshpro.threepeeks.presentation.UiState
import com.sshpro.threepeeks.presentation.photo_list.model.PhotoUiEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosView(
    viewModel: PhotoListViewModel = hiltViewModel()
) {
    when (val state = viewModel.state.value) {
        is UiState.Success<List<PhotoUiEntity>> -> {
            PhotoListView(items = state.data)
        }
        is UiState.Error -> {
            ErrorView(state.message ?: stringResource(id = R.string.default_error))
        }
        is UiState.Loading -> {
            ProgressView()
        }
    }
}