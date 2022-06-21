package com.sshpro.threepeeks.network

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader
import java.net.HttpURLConnection

const val BASE_URL = "http://localhost:8080/test/"

class MockServer {
    companion object {
        val jsonResponseAlbums = readJson("albums.json")
        val jsonResponseAlbum = readJson("album.json")

        val jsonResponsePhotos = readJson("photos.json")
        val jsonResponsePhoto = readJson("photo.json")
        const val jsonResponseEmpty = "{}"
        private fun readJson(path: String): String {
            val inputStream = MockServer::class.java.classLoader?.getResourceAsStream(path)
            return InputStreamReader(inputStream)
                .use { it.readText() }
        }

        fun getServer(): MockWebServer {
            val server = MockWebServer()
            server.dispatcher = TestDispatcher
            server.start(8080)
            server.url(BASE_URL)
            return server
        }

        object TestDispatcher : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                if (request.path?.contains("test/photos?albumId=999") == true)
                    return buildResponse(jsonResponseEmpty)
                else if (request.path?.contains("test/photos") == true)
                    return buildResponse(jsonResponsePhotos)
                else if (request.path?.contains("test/albums") == true)
                    return buildResponse(jsonResponseAlbums)
                return MockResponse().setResponseCode(404)
            }
        }

        private fun buildResponse(body: String): MockResponse {
            return MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(body)
        }
    }

}