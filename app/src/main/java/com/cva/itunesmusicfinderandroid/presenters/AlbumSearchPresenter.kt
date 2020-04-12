package com.cva.itunesmusicfinderandroid.presenters

class AlbumSearchPresenter {
    private val searchedArtist: String
    private val presenterDelegate: AlbumSearchPresenterInterface

    constructor(presenterDelegate: AlbumSearchPresenterInterface,
                searchedArtist: String) {
        this.presenterDelegate = presenterDelegate
        this.searchedArtist = searchedArtist
        println("AlbumSearchPresenter searchedArtist:"+searchedArtist)
        this.presenterDelegate.gotSearchAlbumsServiceError()
    }

}

interface AlbumSearchPresenterInterface {
//    fun gotAlbumsList()
    fun gotSearchAlbumsServiceError()
    fun gotSearchAlbumsConectivityError()
}