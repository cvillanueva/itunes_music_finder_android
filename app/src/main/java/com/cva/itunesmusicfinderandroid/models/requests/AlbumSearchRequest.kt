package com.cva.itunesmusicfinderandroid.models.requests

import com.cva.itunesmusicfinderandroid.resources.constants.Endpoint
import okhttp3.FormBody
import okhttp3.RequestBody


class AlbumSearchRequest {

    /// Searched Artist or Band
    private val term: String
    /// Sort of 'Entity' searched, for exmaple: Album, song, video
    private val entity: String
    /// Amount of items to get
    private val limit: String

    constructor(searchedArtist: String) {
        this.term = searchedArtist
        this.entity = Endpoint.AlbumSearching.defaultEntity
        this.limit = Endpoint.AlbumSearching.resultsMax.toString()
    }

    fun getRequestBody(): RequestBody {
        return FormBody.Builder()
            .add("term", term)
            .add("entity", entity)
            .add("limit", limit)
            .build()
    }
}