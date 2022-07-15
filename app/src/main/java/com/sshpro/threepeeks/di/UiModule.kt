package com.sshpro.threepeeks.di

import com.sshpro.threepeeks.data.Mapper
import com.sshpro.threepeeks.domain.DataState
import com.sshpro.threepeeks.domain.model.Album
import com.sshpro.threepeeks.domain.model.Photo
import com.sshpro.threepeeks.presentation.UiState
import com.sshpro.threepeeks.presentation.album_list.model.AlbumUiEntity
import com.sshpro.threepeeks.presentation.album_list.model.DataStateAlbumMapper
import com.sshpro.threepeeks.presentation.photo_list.model.DataStatePhotoMapper
import com.sshpro.threepeeks.presentation.photo_list.model.PhotoUiEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UiModule {

    @Binds
    abstract fun bindUiStateAlbumMapper(
        mapper: DataStateAlbumMapper
    ): Mapper<DataState<List<Album>>, UiState<List<AlbumUiEntity>>>

    @Binds
    abstract fun bindUiStatePhotoMapper(
        mapper: DataStatePhotoMapper
    ): Mapper<DataState<List<Photo>>, UiState<List<PhotoUiEntity>>>
}