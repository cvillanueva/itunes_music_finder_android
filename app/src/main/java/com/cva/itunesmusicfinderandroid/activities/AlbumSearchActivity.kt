package com.cva.itunesmusicfinderandroid.activities

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.cva.itunesmusicfinderandroid.R
import com.cva.itunesmusicfinderandroid.activities.fragments.ListFragment
import com.cva.itunesmusicfinderandroid.models.entities.AlbumEntity
import com.cva.itunesmusicfinderandroid.presenters.AlbumSearchPresenter
import com.cva.itunesmusicfinderandroid.presenters.AlbumSearchPresenterInterface


class AlbumSearchActivity : AppCompatActivity(), AlbumSearchPresenterInterface {

    private lateinit var searchView: SearchView
    private lateinit var iTunesLogoImageView: ImageView
    private lateinit var listFragment: ListFragment
    private var savedInstanceState: Bundle? = null
    private var albumSearchPresenter: AlbumSearchPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate()")
        setContentView(R.layout.activity_albumsearch)
        this.savedInstanceState = savedInstanceState

        searchView = findViewById(R.id.searchView)
        iTunesLogoImageView = findViewById(R.id.itunesLogo)

        if(savedInstanceState == null) {
                this.listFragment = ListFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, this.listFragment, ListFragment.TAG).commit()
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                println("onQueryTextSubmit() query:"+query)
                println("listFragment:$this.listFragment")

                if (query != null && query != "") {
                    val aniRotateClk: Animation = AnimationUtils.loadAnimation(
                        applicationContext,
                        R.anim.rotate_clockwise
                    )
                    iTunesLogoImageView.startAnimation(aniRotateClk)
                    albumSearchPresenter = AlbumSearchPresenter(presenterDelegate = this@AlbumSearchActivity, searchedArtist = query)

                    this@AlbumSearchActivity.runOnUiThread {
                        listFragment.clear()
                    }
                }

                return false
            }
        })
    }

    override fun gotAlbumsList(albumList: List<AlbumEntity.AlbumItemModel>) {
        this@AlbumSearchActivity.runOnUiThread {
            iTunesLogoImageView.clearAnimation()
            iTunesLogoImageView.visibility = View.GONE
            this.listFragment.addItems(albumsList = albumList)
        }
    }

    override fun gotSearchAlbumsConectivityError() {
        println("gotSearchAlbumsConectivityError()")
    }

    override fun gotSearchAlbumsServiceError() {
        println("gotSearchAlbumsServiceError()")
    }
}
