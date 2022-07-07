package com.sshpro.threepeeks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sshpro.threepeeks.business.AlbumRepository
import com.sshpro.threepeeks.business.DataState
import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.domain.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AlbumRepository
) : ViewModel() {
    private var _albums =  MutableStateFlow<DataState<List<Album>>>(DataState.Loading)
    val albums: StateFlow<DataState<List<Album>>> = _albums

    fun getAlbums() {
        viewModelScope.launch {
            repository.albumsAsFlow.collect {
                _albums.value = it
            }
        }
    }

    private var _photos = MutableStateFlow<DataState<List<Photo>>>(DataState.Loading)
    val photos: StateFlow<DataState<List<Photo>>> = _photos

    fun getPhotos(albumId: Int) {
        viewModelScope.launch {
            repository.photos(albumId).collect {
                _photos.value = it
            }
        }
    }

}