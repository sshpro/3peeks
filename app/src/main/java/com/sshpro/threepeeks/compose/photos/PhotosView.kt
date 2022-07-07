package com.sshpro.threepeeks.compose.photos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sshpro.threepeeks.R
import com.sshpro.threepeeks.business.DataState
import com.sshpro.threepeeks.business.domain.Photo
import com.sshpro.threepeeks.compose.ErrorView
import com.sshpro.threepeeks.compose.ProgressView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosView(
    dataState: DataState<List<Photo>>,
    onClick: (Int) -> Unit = {},
) {
    when (dataState) {
        is DataState.Success<List<Photo>> -> {
            PhotoListView(items = dataState.data, onClick = onClick)
        }
        is DataState.Error -> {
            ErrorView(dataState.exception.message ?: stringResource(id = R.string.default_error))
        }
        is DataState.Loading -> {
            ProgressView()
        }
    }
}