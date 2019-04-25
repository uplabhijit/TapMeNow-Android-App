//package com.example.tapmenow.CustomAdapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import com.example.tapmenow.R
//import kotlinx.android.synthetic.main.grid_item.view.*
//
//class MonthViewAdapter : BaseAdapter {
//    var foodsList = ArrayList<Dates>()
//    var context: Context? = null
//
//    constructor(context: Context, foodsList: ArrayList<Dates>) : super() {
//        this.context = context
//        this.foodsList = foodsList
//    }
//
//
//    override fun getCount(): Int {
//        return foodsList.size
//    }
//
//    override fun getItem(position: Int): Any {
//        return foodsList[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val food = this.foodsList[position]
//
//        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        var foodView = inflator.inflate(R.layout.grid_item, null)
//
//        foodView.tvName.text = food.name?.toString()!!
//
//        return foodView
//    }
//}