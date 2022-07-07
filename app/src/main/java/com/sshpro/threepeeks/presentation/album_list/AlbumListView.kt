package com.sshpro.threepeeks.presentation.album_list

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
import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.presentation.TestTags
import com.sshpro.threepeeks.presentation.album_list.AlbumListItem
import com.sshpro.threepeeks.presentation.album_list.AlbumListItemView

@ExperimentalFoundationApi
@Composable
fun AlbumListView(
    items: List<Album>,
    onClick: (Int) -> Unit = {}
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.default_vertical_spacing)
        ),
        modifier = Modifier.testTag(TestTags.ALBUMS_VIEW),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(
            count = items.size,
            key = { items[it].id }
        ) { index ->
            val album = items[index]
            val item = AlbumListItem(
                id = album.id,
                title = album.title,
                subTitle1 = album.photoTitle,
                subTitle2 = album.user,
                thumbnailUrl = album.thumbnailUrl
            )
            AlbumListItemView(item, onClick = onClick)
        }
    }
}