package com.sshpro.threepeeks.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoAlbum
import androidx.compose.ui.graphics.vector.ImageVector
import com.sshpro.threepeeks.R

enum class Screen(val icon: ImageVector, val descriptionRes: Int) {
    Albums(icon = Icons.Filled.PhotoAlbum, descriptionRes = R.string.content_description_thumbnail_album),
    Photos(icon = Icons.Filled.Photo, descriptionRes = R.string.content_description_thumbnail_photo);

    companion object {
        fun fromRoute(route: String?): Screen =
            when (route?.substringBefore("/")) {
                Albums.name -> Albums
                Photos.name -> Photos
                null -> Albums
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}