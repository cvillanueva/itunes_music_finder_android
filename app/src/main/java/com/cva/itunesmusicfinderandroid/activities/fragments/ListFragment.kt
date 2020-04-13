package com.cva.itunesmusicfinderandroid.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cva.itunesmusicfinderandroid.R
import com.cva.itunesmusicfinderandroid.activities.adapters.ListAdapter
import com.cva.itunesmusicfinderandroid.activities.adapters.OnItemClickListener
import com.cva.itunesmusicfinderandroid.models.entities.AlbumEntity

class ListFragment: Fragment() {

    protected lateinit var rootView: View
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ListAdapter

    companion object {
        var TAG = ListFragment::class.java.simpleName
        const val ARG_POSITION: String = "positioin"

        fun newInstance(): ListFragment {
            var fragment = ListFragment();
            val args = Bundle()
            args.putInt(ARG_POSITION, 1)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateComponent()
    }

    private fun onCreateComponent() {
        adapter = ListAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        initView()
        return rootView
    }

    private fun initView(){
        setUpAdapter()
        initializeRecyclerView()
        setUpDummyData()
    }

    private fun setUpAdapter() {
        adapter.setOnItemClickListener(onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int, view: View?) {
                var album = adapter.getItem(position)
//                startActivity(context?.let {ctx ->
//                    album?.let {
//                            album -> DetailsActivity.newIntent(ctx, user)
//                    }
//                })
            }
        })
    }

    private fun initializeRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    private fun setUpDummyData(){
        var list: ArrayList<AlbumEntity.AlbumItemModel> = ArrayList<AlbumEntity.AlbumItemModel>()
        list.add(AlbumEntity.AlbumItemModel("La espada y la pared", R.drawable.ic_launcher_background))
        list.add(AlbumEntity.AlbumItemModel("Fome", R.drawable.ic_launcher_background))
        list.add(AlbumEntity.AlbumItemModel("ReFome", R.drawable.ic_launcher_background))
        adapter.addItems(list)
    }
}