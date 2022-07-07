package com.sshpro.threepeeks.business.domain

data class Photo(
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
)
