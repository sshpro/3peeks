package com.sshpro.threepeeks.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.sshpro.threepeeks.compose.data.TestData
import com.sshpro.threepeeks.presentation.album_list.AlbumListView
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