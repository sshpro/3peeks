package com.sshpro.threepeeks.domain.use_case

import com.sshpro.threepeeks.domain.DataState
import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.data.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    operator fun invoke(): Flow<DataState<List<Album>>> = flow {
        emit(DataState.Loading)
        emit(DataState.Success(repository.getAlbums()))
    }.catch {  error ->
        emit(DataState.Error(error))
    }
}