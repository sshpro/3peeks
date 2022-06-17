package com.sshpro.threepeeks

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sshpro.threepeeks.business.AlbumRepository
import com.sshpro.threepeeks.business.DataState
import com.sshpro.threepeeks.business.domain.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AlbumRepository
) : ViewModel() {
    val albumsDataState: MutableState<DataState<List<Album>>> = mutableStateOf(DataState.Loading)

    fun getAlbums() {
        viewModelScope.launch {
            try {
                albumsDataState.value = DataState.Loading
                val data = repository.albumsAsFlow.toList();
                albumsDataState.value = DataState.Success(data)
            } catch (exception: Exception) {
                albumsDataState.value = DataState.Error(exception)
            }
        }
    }
}