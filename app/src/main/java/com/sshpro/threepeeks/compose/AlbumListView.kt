package com.sshpro.threepeeks.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.sshpro.threepeeks.business.domain.Album

@ExperimentalFoundationApi
@Composable
fun AlbumListView(albums: List<Album>) {
    LazyVerticalGrid(
        modifier = Modifier.testTag(TestTags.ALBUMS_VIEW),
        contentPadding = PaddingValues(10.dp),
        cells = GridCells.Fixed(1)
    ) {
        items(albums) { album ->
            AlbumItem(album)
        }
    }
}