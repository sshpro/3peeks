package com.sshpro.threepeeks.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sshpro.threepeeks.R
import com.sshpro.threepeeks.business.DataState
import com.sshpro.threepeeks.business.domain.Album

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView(
    dataState: DataState<List<Album>>
) {
    when (dataState) {
        is DataState.Success<List<Album>> -> {
            AlbumListView(albums = dataState.data)
        }
        is DataState.Error -> {
            ErrorView(dataState.exception.message ?: stringResource(id = R.string.default_error))
        }
        is DataState.Loading -> {
            ProgressView()
        }
    }
}