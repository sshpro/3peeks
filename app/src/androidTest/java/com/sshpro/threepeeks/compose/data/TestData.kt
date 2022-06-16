package com.sshpro.threepeeks.compose.data

import com.sshpro.threepeeks.business.domain.Album

object TestData {
    val albums: List<Album> =
        listOf(
            Album(
                id = 1, title = "abcd1", userId = 1,
                thumbnailUrl = "https://via.placeholder.com/150/92c952", photoTitle = "ssa1"
            ),
            Album(
                id = 2, title = "abcd2", userId = 2,
                thumbnailUrl = "https://via.placeholder.com/150/771796", photoTitle = "ssa2"
            ),
            Album(
                id = 3, title = "abcd3", userId = 3,
                thumbnailUrl = "https://via.placeholder.com/150/d32776", photoTitle = "ssa3"
            ),
            Album(
                id = 4, title = "abcd4", userId = 4,
                thumbnailUrl = "https://via.placeholder.com/150/f66b97", photoTitle = "ssa4"
            ),
            Album(
                id = 5, title = "abcd5", userId = 5,
                thumbnailUrl = "https://via.placeholder.com/150/b0f7cc", photoTitle = "ssa5"
            ),
            Album(
                id = 6, title = "abcd6", userId = 6,
                thumbnailUrl = "https://via.placeholder.com/150/56a8c2", photoTitle = "ssa6"
            ),
            Album(
                id = 7, title = "abcd7", userId = 7,
                thumbnailUrl = "https://via.placeholder.com/150/54176f", photoTitle = "ssa7"
            ),
            Album(
                id = 8, title = "abcd8", userId = 8,
                thumbnailUrl = "https://via.placeholder.com/150/810b14", photoTitle = "ssa8"
            ),
            Album(
                id = 9, title = "abcd9", userId = 9,
                thumbnailUrl = "https://via.placeholder.com/150/66b7d2", photoTitle = "ssa9"
            ),
            Album(
                id = 10, title = "abcd10", userId = 10,
                thumbnailUrl = "https://via.placeholder.com/150/1ee8a4", photoTitle = "ssa10"
            ),
            Album(
                id = 11, title = "abcd11", userId = 11,
                thumbnailUrl = "https://via.placeholder.com/150/61a65", photoTitle = "ssa11"
            ),
        )
}