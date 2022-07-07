package com.sshpro.threepeeks.domain.use_case

import com.sshpro.threepeeks.domain.DataState
import com.sshpro.threepeeks.domain.model.Photo
import com.sshpro.threepeeks.data.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotosByAlbumIdUseCase @Inject constructor(
    private val repository: AlbumRepository,
) {
    operator fun invoke(albumId: Int): Flow<DataState<List<Photo>>> = flow {
        emit(DataState.Loading)
        emit(DataState.Success(repository.getPhotos(albumId)))
    }.catch { error ->
        emit(DataState.Error(error))
    }
}