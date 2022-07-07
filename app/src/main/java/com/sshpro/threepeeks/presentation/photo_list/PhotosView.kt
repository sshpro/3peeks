package com.sshpro.threepeeks.presentation.photo_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.sshpro.threepeeks.R
import com.sshpro.threepeeks.domain.DataState
import com.sshpro.threepeeks.domain.model.Photo
import com.sshpro.threepeeks.presentation.ErrorView
import com.sshpro.threepeeks.presentation.ProgressView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosView(
    viewModel: PhotoListViewModel = hiltViewModel()
) {
    when (val state = viewModel.state.value) {
        is DataState.Success<List<Photo>> -> {
            PhotoListView(items = state.data)
        }
        is DataState.Error -> {
            ErrorView(state.exception.message ?: stringResource(id = R.string.default_error))
        }
        is DataState.Loading -> {
            ProgressView()
        }
    }
}