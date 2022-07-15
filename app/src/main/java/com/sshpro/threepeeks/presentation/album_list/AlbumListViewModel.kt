package com.sshpro.threepeeks.presentation.album_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sshpro.threepeeks.domain.DataState
import com.sshpro.threepeeks.data.Mapper
import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.domain.use_case.GetAlbumsUseCase
import com.sshpro.threepeeks.presentation.UiState
import com.sshpro.threepeeks.presentation.album_list.model.AlbumUiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val albumMapper: @JvmSuppressWildcards Mapper<DataState<List<Album>>, UiState<List<AlbumUiEntity>>>,
) : ViewModel() {
    private var _state: MutableState<UiState<List<AlbumUiEntity>>> = mutableStateOf(UiState.Loading)
    val state = _state

    init {
        getAlbums()
    }

    private fun getAlbums() {
        viewModelScope.launch {
            getAlbumsUseCase()
                .map { dataState -> albumMapper.mapToDomain(dataState) }
                .collect { uiState -> _state.value = uiState }
        }
    }
}