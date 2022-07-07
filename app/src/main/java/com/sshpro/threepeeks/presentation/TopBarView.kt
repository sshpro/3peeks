package com.sshpro.threepeeks.presentation

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.sshpro.threepeeks.R

@Composable
fun TopBarView(
    currentScreen: Screen
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