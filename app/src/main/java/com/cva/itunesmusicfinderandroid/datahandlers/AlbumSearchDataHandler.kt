package com.cva.itunesmusicfinderandroid.datahandlers

import com.cva.itunesmusicfinderandroid.apicallers.AlbumSearchApiCall
import com.cva.itunesmusicfinderandroid.apicallers.AlbumSearchApiCallInterface
import com.cva.itunesmusicfinderandroid.models.entities.AlbumEntity
import com.cva.itunesmusicfinderandroid.models.responses.AlbumSearchResponse

class AlbumSearchDataHandler: AlbumSearchApiCallInterface {

    // Delegate to return data to presenter
    private val delegate: AlbumSearchDataHandlerInterface
    // Search param
    private val searchedArtist: String

    constructor(delegate: AlbumSearchDataHandlerInterface,
                searchedArtist: String) {
        this.delegate = delegate
        this.searchedArtist = searchedArtist

        getValidResponse(searchedArtist = this.searchedArtist)
    }

    // Check valid response and returns or call service again
    private fun getValidResponse(searchedArtist: String) {
        println("getValidResponse()")
        val cacheKey = "artist_$searchedArtist"

        // We have to implement cache
        callService()
    }

    private fun callService() {
        println("callService()")
        AlbumSearchApiCall(delegate = this, searchedArtist = this.searchedArtist)
    }

    override fun gotAlbumsList(jsonString: String) {
        val albumsList = AlbumEntity().getAlbumItemListFromResponse(albumListResponse = AlbumSearchResponse(json = jsonString).getAlbumsList())
        this.delegate.gotAlbumsList(albumList = albumsList)
    }

    override fun gotServerError() {
        this.delegate.gotSearchAlbumesServiceError()
    }
}

interface AlbumSearchDataHandlerInterface {
    fun gotAlbumsList(albumList: List<AlbumEntity.AlbumItemModel>)
    fun gotSearchAlbumesServiceError()
    fun gotSearchAlbumsConectivityError()
}