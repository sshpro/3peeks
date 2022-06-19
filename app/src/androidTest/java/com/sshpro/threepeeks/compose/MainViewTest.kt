package com.sshpro.threepeeks.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.sshpro.threepeeks.business.DataState
import com.sshpro.threepeeks.compose.data.TestData
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class MainViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldShowLoadingView() {
        composeTestRule.setContent {
            MainView(dataState = DataState.Loading)
        }

        composeTestRule
            .onNodeWithTag(TestTags.LOADING_VIEW)
            .assertIsDisplayed()
    }

    @Test
    fun shouldShowErrorView() {
        composeTestRule.setContent {
            MainView(dataState = DataState.Error(Exception("Error")))
        }

        composeTestRule
            .onNodeWithTag(TestTags.ERROR_VIEW)
            .assertIsDisplayed()
    }

    @Test
    fun shouldShowAlbumsView() {
        composeTestRule.setContent {
            MainView(dataState = DataState.Success(TestData.albums.first()))
        }

        composeTestRule
            .onNodeWithTag(TestTags.ALBUMS_VIEW)
            .assertIsDisplayed()
    }
}