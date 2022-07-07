package com.sshpro.threepeeks.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserNetworkEntity(
    val id: Int,
    val name: String
)

fun defaultUser(): UserNetworkEntity =
    UserNetworkEntity(id = 0, name = "")
