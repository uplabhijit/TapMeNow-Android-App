package com.example.tapmenow.CustomAdapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tapmenow.Models.Events
import com.example.tapmenow.R
import kotlinx.android.synthetic.main.upcommingtaps_items.view.*

class UpCommingTaps_ViewHolder(val items: ArrayList<Events>, val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0?.title?.text = items.get(p1).title.toString()
        p0?.desc?.text = items.get(p1).year.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.upcommingtaps_items, parent, false))
    }


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val title = view.list_title
    val desc = view.list_description
}
