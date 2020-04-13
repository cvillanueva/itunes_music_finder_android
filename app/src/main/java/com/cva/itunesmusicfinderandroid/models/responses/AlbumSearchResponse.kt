package com.cva.itunesmusicfinderandroid.models.responses

import com.squareup.moshi.*


class AlbumSearchResponse {

    @JsonClass(generateAdapter = true)
    data class AlbumResponse(
        @field:Json(name = "wrapperType") val wrapperType: String?,
        @field:Json(name = "collectionType") val collectionType: String?,
        @field:Json(name = "artistId") val artistId: Int?,
        @field:Json(name = "collectionId") val collectionId: Int,
        @field:Json(name = "amgArtistId") val amgArtistId: String?,
        @field:Json(name = "artistName") val artistName: String?,
        @field:Json(name = "collectionName") val collectionName: String,
        @field:Json(name = "collectionCensoredName") val collectionCensoredName: String?,
        @field:Json(name = "artistViewUrl") val artistViewUrl: String?,
        @field:Json(name = "collectionViewUrl") val collectionViewUrl: String?,
        @field:Json(name = "artworkUrl60") val artworkUrl60: String?,
        @field:Json(name = "artworkUrl100") val artworkUrl100: String?,
        @field:Json(name = "collectionPrice") val collectionPrice: Double?,
        @field:Json(name = "collectionExplicitness") val collectionExplicitness: String?,
        @field:Json(name = "trackCount") val trackCount: Int?,
        @field:Json(name = "copyright") val copyright: String?,
        @field:Json(name = "country") val country: String?,
        @field:Json(name = "currency") val currency: String?,
        @field:Json(name = "releaseDate") val releaseDate: String?,
        @field:Json(name = "primaryGenreName") val primaryGenreName: String?
     )

    @JsonClass(generateAdapter = true)
    class SearchResponse(
        @field:Json(name = "resultCount") val resultCount: Int,
        @field:Json(name = "results") val albumsList: List<AlbumResponse>
    )

    val searchResponse: SearchResponse?

    constructor(json: String) {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<SearchResponse> = moshi.adapter(SearchResponse::class.java)
        searchResponse = jsonAdapter.fromJson(json)
//        println("searchResponse:$searchResponse")
    }

    fun getAlbumsList(): List<AlbumResponse> {
        if (this.searchResponse != null) {
            return this.searchResponse?.albumsList
        } else {
            return listOf<AlbumResponse>()
        }
    }
}