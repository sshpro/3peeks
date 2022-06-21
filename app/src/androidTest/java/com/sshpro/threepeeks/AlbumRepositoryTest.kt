package com.sshpro.threepeeks

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import com.sshpro.threepeeks.business.AlbumRepository
import com.sshpro.threepeeks.business.domain.Album
import com.sshpro.threepeeks.business.network.AlbumNetworkEntity
import com.sshpro.threepeeks.business.network.NetworkMapper
import com.sshpro.threepeeks.business.network.PhotoNetworkEntity
import com.sshpro.threepeeks.network.MockServer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.reactivex.rxjava3.disposables.CompositeDisposable
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
class AlbumRepositoryTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Inject
    lateinit var albumRepository: AlbumRepository

    @Inject
    lateinit var moshi: Moshi

    @Inject
    lateinit var networkMapper: NetworkMapper

    private lateinit var mockServer: MockWebServer

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
    fun shouldGetValidAlbums() {
        val albums = albumRepository.albums.blockingGet()
        assertEquals(albums.size, 100)
        assertEquals(albums[0], getTestAlbum())
    }


    private fun getTestAlbum(): Album {
        val albumNetworkEntity = moshi
            .adapter(AlbumNetworkEntity::class.java)
            .fromJson(MockServer.jsonResponseAlbum)!!

        val photoNetworkEntity = moshi
            .adapter(PhotoNetworkEntity::class.java)
            .fromJson(MockServer.jsonResponsePhoto)!!
        return networkMapper.mapToDomain(albumNetworkEntity, photoNetworkEntity)
    }
}