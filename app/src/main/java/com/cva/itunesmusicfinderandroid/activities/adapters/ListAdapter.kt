package com.cva.itunesmusicfinderandroid.activities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

        private val imageView: ImageView = view.albumArt
        private val textView: TextView = view.albumName

        init {
            view.setOnClickListener(this)
        }

        fun setUpView(album: AlbumEntity.AlbumItemModel?) {
            album?.id?.let { imageView.setImageResource(it) }
            textView.text = album?.name
        }

        override fun onClick(v: View?) {
            itemClickListener?.onItemClick(adapterPosition, v)
        }
    }
}