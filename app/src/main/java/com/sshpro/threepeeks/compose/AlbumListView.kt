package com.sshpro.threepeeks.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.sshpro.threepeeks.R
import com.sshpro.threepeeks.business.domain.Album

@ExperimentalFoundationApi
@Composable
fun AlbumListView(albums: List<Album>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.default_vertical_spacing)
        ),
        modifier = Modifier.testTag(TestTags.ALBUMS_VIEW),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(
            count = albums.size,
            key = { albums[it].id }
        ) { index ->
            AlbumItem(albums[index])
        }
    }
}