package com.sshpro.threepeeks.compose

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoAlbum
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sshpro.threepeeks.R

@Composable
fun TopBarView(
    title: String
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.PhotoAlbum, "")
            }
        },
        backgroundColor = Color.Blue,
        contentColor = Color.White,
        elevation = dimensionResource(id = R.dimen.topbar_elevation)
    )
}