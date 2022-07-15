package com.sshpro.threepeeks

import com.sshpro.threepeeks.domain.DataState
import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.domain.model.Photo
import com.sshpro.threepeeks.presentation.UiState
import com.sshpro.threepeeks.presentation.album_list.model.AlbumUiEntity
import com.sshpro.threepeeks.presentation.album_list.model.DataStateAlbumMapper
import com.sshpro.threepeeks.presentation.photo_list.model.PhotoUiEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class DataStateAlbumMapperTest {
    private val dataStateMapper = DataStateAlbumMapper()

    @Test
    fun shouldMapLoading() {
        val dateState = DataState.Loading;
        val uiState = dataStateMapper.mapToDomain(dateState)
        assertEquals(uiState, UiState.Loading)
    }
    @Test
    fun shouldMapError() {
        val errorString = "Error";
        val dateState = DataState.Error(Exception(errorString))
        val uiState = dataStateMapper.mapToDomain(dateState)
        assertEquals(uiState, UiState.Error(errorString))
    }
    @Test
    fun shouldMapSuccess() {

        val dateState = DataState.Success(albums)
        val uiState: UiState<List<AlbumUiEntity>> = dataStateMapper.mapToDomain(dateState)

        assertEquals(uiState, UiState.Success(albumUiEntity))
    }

    private val albums = listOf(
        Album(1, "user1", "title1", "phototitle1", "thumb1"),
        Album(2, "user2", "title3", "phototitle2", "thumb2"),
        Album(3, "user2", "title3", "phototitle3", "thumb3"),
        )
    private val albumUiEntity = listOf(
        AlbumUiEntity(1, "user1", "title1", "phototitle1", "thumb1"),
        AlbumUiEntity(2, "user2", "title3", "phototitle2", "thumb2"),
        AlbumUiEntity(3, "user2", "title3", "phototitle3", "thumb3"),
    )
}