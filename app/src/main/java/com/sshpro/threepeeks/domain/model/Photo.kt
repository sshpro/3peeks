package com.sshpro.threepeeks.domain.model

data class Photo(
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
)
