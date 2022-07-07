package com.sshpro.threepeeks.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumNetworkEntity(
    val id: Int,
    val userId: Int,
    val title: String
)