package com.sshpro.threepeeks.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoNetworkEntity(
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

fun defaultPhoto(): PhotoNetworkEntity =
    PhotoNetworkEntity(id = 0, albumId = 0, title = "", url = "", thumbnailUrl = "")
