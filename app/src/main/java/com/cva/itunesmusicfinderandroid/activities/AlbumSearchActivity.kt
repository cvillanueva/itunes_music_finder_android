package com.cva.itunesmusicfinderandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import com.cva.itunesmusicfinderandroid.R
import com.cva.itunesmusicfinderandroid.presenters.AlbumSearchPresenter
import com.cva.itunesmusicfinderandroid.presenters.AlbumSearchPresenterInterface

class AlbumSearchActivity : AppCompatActivity(), AlbumSearchPresenterInterface {

    var albumSearchPresenter: AlbumSearchPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView =  findViewById(R.id.searchView) as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                println("onQueryTextSubmit() query:"+query)

                if (query != null && query != "") {
                    albumSearchPresenter = AlbumSearchPresenter(presenterDelegate = this@AlbumSearchActivity, searchedArtist = query)
                }

                return false
            }
        })
    }

    override fun gotSearchAlbumsConectivityError() {
        println("gotSearchAlbumsConectivityError()")
    }

    override fun gotSearchAlbumsServiceError() {
        println("gotSearchAlbumsServiceError()")
    }
}
