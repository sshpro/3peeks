package com.sshpro.threepeeks.presentation.album_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sshpro.threepeeks.domain.DataState
import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.domain.use_case.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
) : ViewModel() {
    private var _state: MutableState<DataState<List<Album>>> = mutableStateOf(DataState.Loading)
    val state = _state

    init {
        getAlbums()
    }

    private fun getAlbums() {
        viewModelScope.launch {
            getAlbumsUseCase().collect {
                _state.value = it
            }
        }
    }
}