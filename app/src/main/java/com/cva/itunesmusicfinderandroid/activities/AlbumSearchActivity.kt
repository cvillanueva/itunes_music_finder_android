package com.cva.itunesmusicfinderandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.cva.itunesmusicfinderandroid.R
import com.cva.itunesmusicfinderandroid.models.entities.AlbumEntity
import com.cva.itunesmusicfinderandroid.presenters.AlbumSearchPresenter
import com.cva.itunesmusicfinderandroid.presenters.AlbumSearchPresenterInterface
import kotlinx.android.synthetic.main.activity_albumsearch.*

class AlbumSearchActivity : AppCompatActivity(), AlbumSearchPresenterInterface {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var searchView: SearchView

    var albumSearchPresenter: AlbumSearchPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albumsearch)

        searchView = findViewById(R.id.searchView)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

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

    override fun gotAlbumsList(albumList: List<AlbumEntity.AlbumItemModel>) {
        println("gotAlbumsList() q:"+albumList.count())
    }

    override fun gotSearchAlbumsConectivityError() {
        println("gotSearchAlbumsConectivityError()")
    }

    override fun gotSearchAlbumsServiceError() {
        println("gotSearchAlbumsServiceError()")
    }
}
