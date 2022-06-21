package com.sshpro.threepeeks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sshpro.threepeeks.business.AlbumRepository
import com.sshpro.threepeeks.business.DataState
import com.sshpro.threepeeks.business.domain.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val
    repository: AlbumRepository

) : ViewModel() {
    private val _albumDataState = mutableStateOf<DataState<List<Album>>>(DataState.Loading)
    val albumState: State<DataState<List<Album>>> = _albumDataState

    fun getAlbums() {
        repository.albums.subscribe(object : Observer<List<Album>> {
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(albums: List<Album>) {
                _albumDataState.value = DataState.Success(albums)
            }

            override fun onError(e: Throwable) {
                _albumDataState.value = DataState.Error(e)
            }

            override fun onComplete() {}
        })
    }
}
