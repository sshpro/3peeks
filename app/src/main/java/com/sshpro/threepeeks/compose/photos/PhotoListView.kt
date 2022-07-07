package com.sshpro.threepeeks.compose.photos

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
import com.sshpro.threepeeks.business.domain.Photo
import com.sshpro.threepeeks.compose.TestTags

@ExperimentalFoundationApi
@Composable
fun PhotoListView(
    items: List<Photo>,
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
            val photo = items[index]
            PhotoListItemView(photo, onClick = onClick)
        }
    }
}