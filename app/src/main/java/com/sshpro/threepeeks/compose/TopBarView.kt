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
import com.sshpro.threepeeks.Screens

@Composable
fun TopBarView(
    currentScreen: Screens
) {
    TopAppBar(
        title = {
            Text(text = currentScreen.name)
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(currentScreen.icon, "")
            }
        },
        backgroundColor = Color.Blue,
        contentColor = Color.White,
        elevation = dimensionResource(id = R.dimen.topbar_elevation)
    )
}