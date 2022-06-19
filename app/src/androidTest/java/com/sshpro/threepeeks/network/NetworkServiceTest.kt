package com.sshpro.threepeeks.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import com.sshpro.threepeeks.business.network.AlbumNetworkEntity
import com.sshpro.threepeeks.business.network.NetworkService
import com.sshpro.threepeeks.business.network.PhotoNetworkEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NetworkServiceTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var moshi:Moshi

    lateinit var mockServer: MockWebServer

    @Before
    fun setup() {
        mockServer = MockServer.getServer()
        rule.inject()
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun shouldReceiveValidAlbums() = runBlocking {
        val albums = networkService.getAlbums()
        assertNotNull(albums)
        assertEquals(albums.size, 100)
        assertEquals(albums[0], getTestAlbumNetworkEntity())
    }

    private fun getTestAlbumNetworkEntity(): AlbumNetworkEntity? {
        val adapter = moshi.adapter(AlbumNetworkEntity::class.java)
        return adapter.fromJson(MockServer.jsonResponseAlbum)
    }

    @Test
    fun shouldReceiveValidPhotos() = runBlocking {
        val photo = networkService.getPhotos()
        assertNotNull(photo)
        assertEquals(photo.size, 10)
        assertEquals(photo[0], getTestPhotoNetworkEntity())
    }

    private fun getTestPhotoNetworkEntity(): PhotoNetworkEntity? {
        val adapter = moshi.adapter(PhotoNetworkEntity::class.java)
        return adapter.fromJson(MockServer.jsonResponsePhoto)
    }

    @Test
    fun shouldReceivePhotosForAlbum() = runBlocking {
        val photos = networkService.getPhotos(albumId = 1)
        assertNotNull(photos)
        assertEquals(photos.size, 10)
        assertEquals(photos[0], getTestPhotoNetworkEntity())
    }
}