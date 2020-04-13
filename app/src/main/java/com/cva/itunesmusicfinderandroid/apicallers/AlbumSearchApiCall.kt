package com.cva.itunesmusicfinderandroid.apicallers

import com.cva.itunesmusicfinderandroid.models.requests.AlbumSearchRequest
import com.cva.itunesmusicfinderandroid.resources.constants.Endpoint
import okhttp3.*
import java.io.IOException


class AlbumSearchApiCall {

    // Attemps amount limit
    private val RETRIES = 3
    // Conection attempts counter
    private var attempt = 0
    // Delegate to return data
    val delegate: AlbumSearchApiCallInterface
    // Search param
    private val searchedArtist: String

    constructor(delegate: AlbumSearchApiCallInterface,
                searchedArtist: String) {
        this.delegate = delegate
        this.searchedArtist = searchedArtist

        val parameters = AlbumSearchRequest(searchedArtist = searchedArtist)
        callService(url = Endpoint.AlbumSearching.url, parameters = parameters, delegate = this.delegate)
    }

    private fun callService(url: String,
                            parameters: AlbumSearchRequest,
                            delegate: AlbumSearchApiCallInterface) {
        println("callService()")
        val client = OkHttpClient()
        val formBody = parameters.getRequestBody()
        val request = Request.Builder().url(url).post(formBody).build()

        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    delegate.gotServerError()
                }

                override fun onResponse(call: Call, response: Response) {
                    val jsonString = response.body?.string()

                    if (jsonString != null) {
                        println("onResponse() response:$jsonString")
                        delegate.gotAlbumsList(jsonString = jsonString)
                    }
                }
            })
    }
}

interface AlbumSearchApiCallInterface {
    fun gotAlbumsList(jsonString: String)
    fun gotServerError()
//    fun gotConectivityError()
}