package com.sshpro.threepeeks.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.sshpro.threepeeks.presentation.album_list.AlbumListView
import com.sshpro.threepeeks.compose.data.TestData
import org.junit.Rule
import org.junit.Test

class AlbumListViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalFoundationApi::class)
    @Test
    fun testAlbumListView() {
        composeTestRule.setContent {
            AlbumListView(items = TestData.albums)
        }

        composeTestRule
            .onNodeWithText(TestData.albums.first().photoTitle)
            .assertIsDisplayed()
    }
}