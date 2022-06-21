package com.sshpro.threepeeks.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.sshpro.threepeeks.business.network.AlbumNetworkEntity
import com.sshpro.threepeeks.business.network.NetworkService
import com.sshpro.threepeeks.business.network.PhotoNetworkEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.TestObserver
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
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
    lateinit var moshi: Moshi

    lateinit var mockServer: MockWebServer

    private val compositeDisposable = CompositeDisposable()

    @Before
    fun setup() {
        mockServer = MockServer.getServer()
        rule.inject()
    }

    @After
    fun tearDown() {
        compositeDisposable.clear()
        mockServer.shutdown()
    }

    @Test
    fun shouldReceiveValidAlbums() {
        val albums = networkService
            .getAlbums()
            .blockingSingle()
            assertEquals(albums.size, 100)
            assertEquals(albums[0], getTestAlbumNetworkEntity())
    }

    private fun getTestAlbumNetworkEntity(): AlbumNetworkEntity? {
        val adapter = moshi.adapter(AlbumNetworkEntity::class.java)
        return adapter.fromJson(MockServer.jsonResponseAlbum)
    }

    @Test
    fun shouldReceiveValidPhotos() {
       val photos = networkService
            .getPhotos()
            .blockingSingle()
        assertEquals(photos.size, 10)
        assertEquals(photos[0], getTestPhotoNetworkEntity())
    }

    private fun getTestPhotoNetworkEntity(): PhotoNetworkEntity? {
        val adapter = moshi.adapter(PhotoNetworkEntity::class.java)
        return adapter.fromJson(MockServer.jsonResponsePhoto)
    }

    @Test
    fun shouldReceivePhotosForAlbum() {
        val photos = networkService
            .getPhotos(albumId = 1)
            .blockingSingle()
        assertEquals(photos.size, 10)
        assertEquals(photos[0], getTestPhotoNetworkEntity())
    }

    @Test
    fun shouldReceiveError() {
        val testObserver = TestObserver<List<PhotoNetworkEntity>>()
        networkService
            .getPhotos(albumId = 999)
            .subscribe(testObserver)
        testObserver.await()
        testObserver.assertError(JsonDataException::class.java)
    }
}