package com.sshpro.threepeeks.presentation.album_list.model


import com.sshpro.threepeeks.data.Mapper
import com.sshpro.threepeeks.domain.DataState
import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.presentation.UiState
import javax.inject.Inject

class DataStateAlbumMapper
@Inject
constructor() :
    Mapper<@JvmSuppressWildcards DataState<List<Album>>,
            @JvmSuppressWildcards UiState<List<AlbumUiEntity>>> {

    override fun mapToDomain(
        input: DataState<List<Album>>
    ): UiState<List<AlbumUiEntity>> {
        return when (input) {
            is DataState.Success<List<Album>> -> {
                val albums = input.data.map {
                    AlbumUiEntity(id = it.id,
                        user = it.user,
                        title = it.title,
                        photoTitle = it.photoTitle,
                        thumbnailUrl = it.thumbnailUrl)
                }
                UiState.Success(albums)
            }
            is DataState.Loading -> UiState.Loading
            is DataState.Error -> UiState.Error(input.exception.message)
        }
    }
}