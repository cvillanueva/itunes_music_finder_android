package com.cva.itunesmusicfinderandroid.models.entities

import com.cva.itunesmusicfinderandroid.models.responses.AlbumSearchResponse

class AlbumEntity {
    data class AlbumItemModel(val name: String, val id: Int)

    fun getAlbumItemListFromResponse(albumListResponse: List<AlbumSearchResponse.AlbumResponse>): List<AlbumItemModel> {
        val albumItemModelList = mutableListOf<AlbumItemModel>()

        for (albumResponse in albumListResponse) {
            albumItemModelList.add(
                AlbumItemModel(
                name = albumResponse.collectionName,
                id = albumResponse.collectionId
            ))
        }
        return albumItemModelList
    }
}