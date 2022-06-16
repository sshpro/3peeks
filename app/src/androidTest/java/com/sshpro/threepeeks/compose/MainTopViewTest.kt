package com.sshpro.threepeeks.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import org.junit.Rule
import org.junit.Test

class MainTopViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTopAppBarShowsAlbums() {
        composeTestRule.setContent { 
            TopBarView(testTitle)
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule
            .onNodeWithText(testTitle)
            .assertIsDisplayed()
    }

    companion object {
        const val testTitle = "Test"
    }
}