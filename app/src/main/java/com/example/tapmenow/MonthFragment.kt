package com.example.tapmenow

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.example.tapmenow.CustomAdapter.GridCellAdapter
import com.example.tapmenow.SwipeListener.OnSwipeTouchListener
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class MonthFragment : Fragment(), View.OnClickListener {
    /*  override fun onGlobalLayout() {
          if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
              this.calendarView.getViewTreeObserver().removeGlobalOnLayoutListener(this)
          } else {
              this.calendarView.getViewTreeObserver().removeOnGlobalLayoutListener(this)
          }
          width = calendarView.getMeasuredWidth()
          height = calendarView.getMeasuredHeight()
          println("height" + height)
      }*/


    internal lateinit var view: View
    var width: Int = 0
    var height: Int = 0
    internal lateinit var prevMonth: ImageView
    internal lateinit var currentMonth: TextView
    internal lateinit var nextMonth: ImageView
    internal lateinit var calendarView: GridView
    internal lateinit var adapter: GridCellAdapter
    internal var dateTemplate = "MMMM yyyy"
    internal lateinit var _calendar: Calendar
    private var month: Int = 0
    private var year: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        view = inflater.inflate(R.layout.fragment_tab_month, container, false)
        initUiElements()





        calendarView.setOnTouchListener(object : OnSwipeTouchListener() {
            override fun onSwipeLeft() {
                //next month
                if (month > 11) {
                    month = 1
                    year++
                } else {
                    month++
                }
                Log.d("TAG", "Setting Next Month in GridCellAdapter: Month: $month Year: $year")
                setGridCellAdapterToDate(month, year)
                println("OnSwipeLeft" + month + year)
                (activity as DashboardActivity).setDateToolbar(
                    month, year.toString()
                )
            }

            override fun onSwipeRight() {
                //previous month
                if (month <= 1) {
                    month = 12
                    year--
                } else {
                    month--
                }
                Log.d("TAG", "Setting Prev Month in GridCellAdapter: Month: $month Year: $year")
                setGridCellAdapterToDate(month, year)

                println("OnSwipeRight" + month + year)
                (activity as DashboardActivity).setDateToolbar(month, year.toString())
            }
        })



        return view
    }

    //
    private fun initUiElements() {

        _calendar = Calendar.getInstance(Locale.getDefault())
        month = _calendar.get(Calendar.MONTH) + 1
        year = _calendar.get(Calendar.YEAR)
        prevMonth = view.findViewById<View>(R.id.prevMonth) as ImageView
        prevMonth.setOnClickListener(this)
        currentMonth = view.findViewById<View>(R.id.currentMonth) as TextView
        currentMonth.text = DateFormat.format(dateTemplate, _calendar.time)
        nextMonth = view.findViewById<View>(R.id.nextMonth) as ImageView
        nextMonth.setOnClickListener(this)

        calendarView = view.findViewById<View>(R.id.calendar) as GridView

        // Initialised
        adapter = GridCellAdapter(this.context!!, R.id.calendar_day_gridcell, month, year)

        adapter.notifyDataSetChanged()
        calendarView.adapter = adapter

    }

    /**
     * @param month
     * @param year
     */
    private fun setGridCellAdapterToDate(month: Int, year: Int) {
        adapter = GridCellAdapter(this.context!!, R.id.calendar_day_gridcell, month, year)
        _calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH))
        currentMonth.text = DateFormat.format(dateTemplate, _calendar.time)
        adapter.notifyDataSetChanged()
        calendarView.adapter = adapter
    }

    override fun onClick(v: View) {
        if (v === prevMonth) {
            if (month <= 1) {
                month = 12
                year--
            } else {
                month--
            }
            Log.d("TAG", "Setting Prev Month in GridCellAdapter: Month: $month Year: $year")
            setGridCellAdapterToDate(month, year)
        }
        if (v === nextMonth) {
            if (month > 11) {
                month = 1
                year++
            } else {
                month++
            }
            Log.d("TAG", "Setting Next Month in GridCellAdapter: Month: $month Year: $year")
            setGridCellAdapterToDate(month, year)
        }

    }

    override fun onDestroy() {
        Log.d("TAG", "Destroying View ...")
        super.onDestroy()
    }
}

