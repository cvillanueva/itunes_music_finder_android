package com.cva.itunesmusicfinderandroid.resources.constants

/// iTunes API searching services endpoints
class Endpoint {

    // For example: https://itunes.apple.com/search?term=muse&&entity=album
    // collectionId field has to be used for songs lookup
    // Albums searching
    class AlbumSearching {
        companion object {
            const val url = "https://itunes.apple.com/search"
            const val resultsMax = 200
            const val defaultEntity = "album"
        }
    }

    /// For example: https://itunes.apple.com/lookup?id=992221994&entity=song
    /// Songs searching using id
    class SongSearching {
        companion object {
            const val url = "https://itunes.apple.com/lookup"
            const val defaultEntity = "song"
        }
    }
}