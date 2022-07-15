package com.sshpro.threepeeks.presentation.album_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.sshpro.threepeeks.R

data class AlbumListItem(
    val id:Int,
    val title: String,
    val subTitle1: String,
    val subTitle2: String,
    val thumbnailUrl: String,
)

@Composable
fun AlbumListItemView(
    item: AlbumListItem ,
    onClick: (Int) -> Unit = {},
) {
    Card(
        elevation = dimensionResource(id = R.dimen.card_elevation),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
            .padding(bottom = dimensionResource(id = R.dimen.card_bottom_margin))
            .clickable(onClickLabel = stringResource(id = R.string.action_see_photos)) {
                onClick(item.id)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = item.thumbnailUrl,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(id = R.string.content_description_thumbnail_album),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(dimensionResource(id = R.dimen.photo_height))
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_default))
            ) {
                Text(item.title, style = MaterialTheme.typography.h5)
                Text(item.subTitle1, style = MaterialTheme.typography.subtitle1)
                Text(item.subTitle2, style = MaterialTheme.typography.subtitle2)
            }
        }
    }
}