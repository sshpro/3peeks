package com.sshpro.threepeeks.business.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoNetworkEntity(
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
