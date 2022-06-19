package com.sshpro.threepeeks.compose

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
import com.sshpro.threepeeks.business.domain.Album

@Composable
fun AlbumItem(
    album: Album
) {
    Card(
        elevation = dimensionResource(id = R.dimen.card_elevation),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
            .padding(bottom = dimensionResource(id = R.dimen.card_bottom_margin))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = album.thumbnailUrl,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(id = R.string.content_description_thumbnail),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(dimensionResource(id = R.dimen.photo_height))
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_default))
            ) {
                Text(album.photoTitle, style = MaterialTheme.typography.h5)
                Text(album.title, style = MaterialTheme.typography.subtitle1)
                Text(album.userId.toString(), style = MaterialTheme.typography.subtitle2)
            }
        }
    }
}