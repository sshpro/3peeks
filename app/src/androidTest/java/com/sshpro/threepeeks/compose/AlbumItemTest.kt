package com.sshpro.threepeeks.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.sshpro.threepeeks.compose.data.TestData
import com.sshpro.threepeeks.presentation.album_list.AlbumListItem
import com.sshpro.threepeeks.presentation.album_list.AlbumListItemView
import org.junit.Rule
import org.junit.Test

class AlbumItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val album = TestData.albums.first()
    private val albumListItem = AlbumListItem(
        id = album.id,
        title = album.title,
        subTitle1 = album.photoTitle,
        subTitle2 = album.user,
        thumbnailUrl = album.thumbnailUrl,
    )

    private fun setContent() {
        composeTestRule.setContent {
            AlbumListItemView(albumListItem)
        }
    }

    @Test
    fun testImageDisplayed() {
        setContent()
        composeTestRule
            .onNodeWithContentDescription("Thumbnail")
            .assertIsDisplayed()
    }

    @Test
    fun testUserIdDisplayed() {
        setContent()
        composeTestRule
            .onNodeWithText(album.user)
            .assertIsDisplayed()
    }

    @Test
    fun testAlbumTitleDisplayed() {
        setContent()
        composeTestRule
            .onNodeWithText(album.title)
            .assertIsDisplayed()
    }

    @Test
    fun testPhotoTitleDisplayed() {
        setContent()
        composeTestRule
            .onNodeWithText(album.photoTitle)
            .assertIsDisplayed()
    }
}