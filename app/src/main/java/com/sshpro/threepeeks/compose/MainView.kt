package com.sshpro.threepeeks.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.sshpro.threepeeks.R
import com.sshpro.threepeeks.business.DataState
import com.sshpro.threepeeks.business.domain.Album

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView(
    dataState: DataState<Album>
) {
    when (dataState) {
        is DataState.Success<Album> -> {
            val albums = remember { mutableListOf<Album>()}
            albums.add(dataState.data)
            AlbumListView(albums = albums)
        }
        is DataState.Error -> {
            ErrorView(dataState.exception.message ?: stringResource(id = R.string.default_error))
        }
        is DataState.Loading -> {
            ProgressView()
        }
    }
}