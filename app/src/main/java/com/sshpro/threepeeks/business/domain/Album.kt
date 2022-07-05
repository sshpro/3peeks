package com.sshpro.threepeeks.business.domain
data class Album(
    val id: Int,
    val user: String,
    val title: String,
    val photoTitle: String,
    val thumbnailUrl: String
)