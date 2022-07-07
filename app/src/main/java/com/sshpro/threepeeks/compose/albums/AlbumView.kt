package com.sshpro.threepeeks.compose.albums

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sshpro.threepeeks.R
import com.sshpro.threepeeks.business.DataState
import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.compose.ErrorView
import com.sshpro.threepeeks.compose.ProgressView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumView(
    dataState: DataState<List<Album>>,
    onAlbumClick: (Int) -> Unit = {},
) {
    when (dataState) {
        is DataState.Success<List<Album>> -> {
            AlbumListView(items = dataState.data, onClick = onAlbumClick)
        }
        is DataState.Error -> {
            ErrorView(dataState.exception.message ?: stringResource(id = R.string.default_error))
        }
        is DataState.Loading -> {
            ProgressView()
        }
    }
}