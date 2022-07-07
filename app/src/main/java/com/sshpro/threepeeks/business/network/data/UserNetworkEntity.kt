package com.sshpro.threepeeks.business.network.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserNetworkEntity(
    val id: Int,
    val name: String
)

fun defaultUser(): UserNetworkEntity =
    UserNetworkEntity(id = 0, name = "")
