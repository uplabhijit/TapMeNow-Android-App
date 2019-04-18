package com.example.tapmenow


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.tapmenow.CustomAdapter.Dates
import com.example.tapmenow.CustomAdapter.MonthViewAdapter
import java.util.*


/**
 * A simple [Fragment] subclass.
 */


class MonthFragment : Fragment() {

    var dayOfWeek_firstDayOfMonth: Int = 0
    var monthAsInt: Int = 0
    var yearAsInt: Int = 0
    var dayAsInt: Int = 0
    var numofDays: Int = 0


    internal lateinit var view: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater!!.inflate(R.layout.fragment_tab_month, container, false)
        initUiElements()
        calendarData()
        getMonthDays(monthAsInt + 1, yearAsInt)//month+1 is for current month //change month here
        dayOfWeek(monthAsInt, yearAsInt)//change month here
        putViews()
        println("numofDays :" + numofDays)
        println("monthAsInt :" + monthAsInt)
        println("yearAsInt :" + yearAsInt)
        println("dayAsInt :" + dayAsInt)
        println("dayOfWeek_firstDayOfMonth :" + dayOfWeek_firstDayOfMonth)

        return view
    }

    private fun initUiElements() {


    }

    private fun putViews() {
        val gridview = view.findViewById(R.id.gridView) as GridView
        var adapter: MonthViewAdapter? = null
        var datesList = ArrayList<Dates>()


        // load dates
        for (i in 1..numofDays) {

            datesList.add(Dates(i))


        }


        gridview.adapter = MonthViewAdapter(this.activity!!, datesList)


    }


    fun calendarData(): Any? {
        val calendar = Calendar.getInstance()
        //Current Month //starts from 0
        monthAsInt = calendar.get(Calendar.MONTH)
        //Current Year
        yearAsInt = calendar.get(Calendar.YEAR)
        //Current Day
        dayAsInt = calendar.get(Calendar.DAY_OF_MONTH)
        //Total no of days in Current Month

        return null

    }

    fun getMonthDays(month: Int, year: Int): Int {

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            numofDays = 30
        } else {
            if (month == 2) {
                numofDays = if (year % 4 == 0) 29 else 28
            } else {
                numofDays = 31
            }
        }
        return numofDays
    }


    fun dayOfWeek(month: Int, year: Int): String {

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        dayOfWeek_firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)
        return dayOfWeek_firstDayOfMonth.toString()
    }

}