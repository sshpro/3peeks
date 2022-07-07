package com.sshpro.threepeeks.presentation.photo_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sshpro.threepeeks.domain.DataState
import com.sshpro.threepeeks.domain.model.Photo
import com.sshpro.threepeeks.domain.use_case.GetPhotosByAlbumIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PARAM_ALBUM_ID = "albumId"


@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val getPhotosByAlbumIdUseCase: GetPhotosByAlbumIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _state: MutableState<DataState<List<Photo>>> = mutableStateOf(DataState.Loading)
    val state = _state

    init {
        savedStateHandle.get<Int>(PARAM_ALBUM_ID)?.let { albumId ->
            getPhotos(albumId)
        }
    }
    private fun getPhotos(albumId: Int) {
        viewModelScope.launch {
            getPhotosByAlbumIdUseCase(albumId).collect {
                _state.value = it
            }
        }
    }
}