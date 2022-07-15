package com.sshpro.threepeeks.presentation.photo_list.model


import com.sshpro.threepeeks.data.Mapper
import com.sshpro.threepeeks.domain.DataState
import com.sshpro.threepeeks.domain.model.Photo
import com.sshpro.threepeeks.presentation.UiState
import javax.inject.Inject

class DataStatePhotoMapper @Inject constructor() :
    Mapper<@JvmSuppressWildcards DataState<List<Photo>>,
            @JvmSuppressWildcards UiState<List<PhotoUiEntity>>> {

    override fun mapToDomain(
        input: DataState<List<Photo>>
    ): UiState<List<PhotoUiEntity>> {
        return when (input) {
            is DataState.Success<List<Photo>> -> {
                val albums = input.data.map {
                    PhotoUiEntity(id = it.id,
                        albumId = it.albumId,
                        title = it.title,
                        url = it.url)
                }
                UiState.Success(albums)
            }
            is DataState.Loading -> UiState.Loading
            is DataState.Error -> UiState.Error(input.exception.message)
        }
    }
}