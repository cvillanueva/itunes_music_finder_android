package com.cva.itunesmusicfinderandroid.presenters

import com.cva.itunesmusicfinderandroid.datahandlers.AlbumSearchDataHandler
import com.cva.itunesmusicfinderandroid.datahandlers.AlbumSearchDataHandlerInterface
import com.cva.itunesmusicfinderandroid.models.entities.AlbumEntity

class AlbumSearchPresenter: AlbumSearchDataHandlerInterface {

    // Delegate to return data to activity
    private val presenterDelegate: AlbumSearchPresenterInterface
    // Search param
    private val searchedArtist: String


    constructor(presenterDelegate: AlbumSearchPresenterInterface,
                searchedArtist: String) {
        this.presenterDelegate = presenterDelegate
        this.searchedArtist = searchedArtist
        println("AlbumSearchPresenter searchedArtist:$searchedArtist")
        AlbumSearchDataHandler(delegate = this, searchedArtist = this.searchedArtist)
    }

    // Returns a list of albums to controller
    // - Parameter albumList: Album list received from data handler
    override fun gotAlbumsList(albumList: List<AlbumEntity.AlbumItemModel>) {
        println("gotAlbumsList() albumsList.count:"+albumList.count())
    }

    // Returns a server error to presenter
    override fun gotSearchAlbumesServiceError() {
        this.presenterDelegate.gotSearchAlbumsServiceError()
    }

    // Returns a connectivity error to presenter
    override fun gotSearchAlbumsConectivityError() {
        this.presenterDelegate.gotSearchAlbumsConectivityError()
    }

}

interface AlbumSearchPresenterInterface {
    fun gotAlbumsList(albumList: List<AlbumEntity.AlbumItemModel>)
    fun gotSearchAlbumsServiceError()
    fun gotSearchAlbumsConectivityError()
}