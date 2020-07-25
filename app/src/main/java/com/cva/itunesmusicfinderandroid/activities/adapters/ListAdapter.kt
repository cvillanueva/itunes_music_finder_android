package com.cva.itunesmusicfinderandroid.activities.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cva.itunesmusicfinderandroid.R
import com.cva.itunesmusicfinderandroid.models.entities.AlbumEntity
import kotlinx.android.synthetic.main.recycler_item_row.view.*


class ListAdapter: BaseRecyclerViewAdapter<AlbumEntity.AlbumItemModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.recycler_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var myHolder = holder as? MyViewHolder
        myHolder?.setUpView(album = getItem(position))
    }

    inner class MyViewHolder (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val view = view

        init {
            view.setOnClickListener(this)
        }

        fun setUpView(album: AlbumEntity.AlbumItemModel?) {
            view.album_item_name.text = album?.name
            view.album_item_artist.text = album?.artist
            view.album_item_genreyear.text = album?.genreAndYear

            if (album?.artworkUrl != null) {
                Glide.with(view.context).load(album?.artworkUrl).into(view.album_item_art);
            }

            if (album?.odd!!) {
                view.setBackgroundColor(Color.argb(1, 225, 225, 225))
            } else {
                view.setBackgroundColor(Color.WHITE)
            }

        }

        override fun onClick(v: View?) {
            itemClickListener?.onItemClick(adapterPosition, v)
        }
    }
}