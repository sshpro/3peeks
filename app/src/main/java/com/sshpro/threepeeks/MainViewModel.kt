package com.sshpro.threepeeks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sshpro.threepeeks.business.AlbumRepository
import com.sshpro.threepeeks.business.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: AlbumRepository
) : ViewModel() {
    val albums = repository.albumsAsFlow
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = DataState.Loading
        )
}