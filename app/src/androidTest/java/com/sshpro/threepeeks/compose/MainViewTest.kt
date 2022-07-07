package com.sshpro.threepeeks.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.sshpro.threepeeks.domain.DataState
import com.sshpro.threepeeks.presentation.album_list.AlbumView
import com.sshpro.threepeeks.compose.data.TestData
import com.sshpro.threepeeks.presentation.TestTags
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class MainViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldShowLoadingView() {
        composeTestRule.setContent {
            AlbumView(dataState = DataState.Loading)
        }

        composeTestRule
            .onNodeWithTag(TestTags.LOADING_VIEW)
            .assertIsDisplayed()
    }

    @Test
    fun shouldShowErrorView() {
        composeTestRule.setContent {
            AlbumView(dataState = DataState.Error(Exception("Error")))
        }

        composeTestRule
            .onNodeWithTag(TestTags.ERROR_VIEW)
            .assertIsDisplayed()
    }

    @Test
    fun shouldShowAlbumsView() {
        composeTestRule.setContent {
            AlbumView(dataState = DataState.Success(TestData.albums))
        }

        composeTestRule
            .onNodeWithTag(TestTags.ALBUMS_VIEW)
            .assertIsDisplayed()
    }
}