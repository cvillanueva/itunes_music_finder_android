package com.cva.itunesmusicfinderandroid.models.entities

import com.cva.itunesmusicfinderandroid.models.responses.AlbumSearchResponse

class AlbumEntity {
    data class AlbumItemModel(val name: String, val id: Int, val artworkUrl: String?, val odd: Boolean)

    fun getAlbumItemListFromResponse(albumListResponse: List<AlbumSearchResponse.AlbumResponse>): List<AlbumItemModel> {
        val albumItemModelList = mutableListOf<AlbumItemModel>()
        var odd = true

        for (albumResponse in albumListResponse) {
            albumItemModelList.add(
                AlbumItemModel(
                name = albumResponse.collectionName,
                id = albumResponse.collectionId,
                artworkUrl =  albumResponse.artworkUrl100,
                odd = odd
            ))
            odd = !odd
        }
        return albumItemModelList
    }
}