package com.sshpro.threepeeks.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.sshpro.threepeeks.presentation.Screen
import com.sshpro.threepeeks.presentation.TopBarView
import org.junit.Rule
import org.junit.Test

class MainTopViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTopAppBarShowsAlbums() {
        composeTestRule.setContent { 
            TopBarView(Screen.Albums)
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule
            .onNodeWithText(Screen.Albums.name)
            .assertIsDisplayed()
    }
}