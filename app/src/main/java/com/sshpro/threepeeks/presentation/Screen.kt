package com.sshpro.threepeeks.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoAlbum
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(val icon: ImageVector) {
    Albums(icon = Icons.Filled.PhotoAlbum),
    Photos(icon = Icons.Filled.Photo);

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