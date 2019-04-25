package com.example.tapmenow.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tapmenow.CustomAdapters.UpCommingTaps_ViewHolder
import com.example.tapmenow.Models.Events
import com.example.tapmenow.R


class UpCommingTapsFragment : Fragment() {
    private val mNicolasCageMovies = listOf(
        Events("Raising Arizona", 1987),
        Events("Vampire's Kiss", 1988),
        Events("Con Air", 1997),
        Events("Gone in 60 Seconds", 1997),
        Events("National Treasure", 2004),
        Events("The Wicker Man", 2006),
        Events("Ghost Rider", 2007),
        Events("Knowing", 2009)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_upcommingtaps, container, false)
        var list_recycler_view = rootView.findViewById(R.id.list_recycler_view) as RecyclerView // Add this
        list_recycler_view.layoutManager = LinearLayoutManager(activity)
        val data = ArrayList<Events>()
        data.add(Events("Raising Arizona", 1987))
        data.add(Events("Raising Arizona", 1987))
        data.add(Events("Raising Arizona", 1987))
        data.add(Events("Raising Arizona", 1987))
        data.add(Events("Raising Arizona", 1987))
        data.add(Events("Raising Arizona", 1987))
        data.add(Events("Raising Arizona", 1987))
        data.add(Events("Raising Arizona", 1987))
        val adapter = UpCommingTaps_ViewHolder(data, this!!.context!!)

        //now adding the adapter to recyclerview
        list_recycler_view.adapter = adapter
        return rootView
    }

    companion object {
        fun newInstance(): UpCommingTapsFragment = UpCommingTapsFragment()
    }


}