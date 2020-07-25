package com.cva.itunesmusicfinderandroid.models.entities

import com.cva.itunesmusicfinderandroid.models.responses.AlbumSearchResponse

class AlbumEntity {
    data class AlbumItemModel(val artist: String?, val name: String, val id: Int, val artworkUrl: String?, val genreAndYear: String?, val odd: Boolean)

    fun getAlbumItemListFromResponse(albumListResponse: List<AlbumSearchResponse.AlbumResponse>): List<AlbumItemModel> {
        val albumItemModelList = mutableListOf<AlbumItemModel>()
        var odd = true

        for (albumResponse in albumListResponse) {
            albumItemModelList.add(
                AlbumItemModel(
                    artist = albumResponse.artistName,
                    name = albumResponse.collectionName,
                    id = albumResponse.collectionId,
                    artworkUrl =  albumResponse.artworkUrl100,
                    genreAndYear = albumResponse.primaryGenreName+" - "+extractYear(albumResponse.releaseDate),
                    odd = odd
            ))
            odd = !odd
        }
        return albumItemModelList
    }

    private fun extractYear(releaseDate: String?): String? = releaseDate?.substring(0,4)
}