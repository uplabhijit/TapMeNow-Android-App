package com.example.tapmenow


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*


/**
 * A simple [Fragment] subclass.
 */


class MonthFragment : Fragment() {

    var dayOfWeek_firstDayOfMonth: Int = 0
    var monthAsInt: Int? = null
    var yearAsInt: Int? = null
    var dayAsInt: Int? = null
    var numofDays: Int = 0

    internal lateinit var view: View
    internal lateinit var day_0: LinearLayout
    internal lateinit var day_1: LinearLayout
    internal lateinit var day_2: LinearLayout
    internal lateinit var day_3: LinearLayout
    internal lateinit var day_4: LinearLayout
    internal lateinit var day_5: LinearLayout
    internal lateinit var day_6: LinearLayout
    internal lateinit var day_7: LinearLayout
    internal lateinit var day_8: LinearLayout
    internal lateinit var day_9: LinearLayout
    internal lateinit var day_10: LinearLayout
    internal lateinit var day_11: LinearLayout
    internal lateinit var day_12: LinearLayout
    internal lateinit var day_13: LinearLayout
    internal lateinit var day_14: LinearLayout
    internal lateinit var day_15: LinearLayout
    internal lateinit var day_16: LinearLayout
    internal lateinit var day_17: LinearLayout
    internal lateinit var day_18: LinearLayout
    internal lateinit var day_19: LinearLayout
    internal lateinit var day_20: LinearLayout
    internal lateinit var day_21: LinearLayout
    internal lateinit var day_22: LinearLayout
    internal lateinit var day_23: LinearLayout
    internal lateinit var day_24: LinearLayout
    internal lateinit var day_25: LinearLayout
    internal lateinit var day_26: LinearLayout
    internal lateinit var day_27: LinearLayout
    internal lateinit var day_28: LinearLayout
    internal lateinit var day_29: LinearLayout
    internal lateinit var day_30: LinearLayout
    internal lateinit var day_31: LinearLayout
    internal lateinit var day_32: LinearLayout
    internal lateinit var day_33: LinearLayout
    internal lateinit var day_34: LinearLayout
    internal lateinit var day_35: LinearLayout
    internal lateinit var day_36: LinearLayout
    internal lateinit var day_37: LinearLayout
    internal lateinit var day_38: LinearLayout
    internal lateinit var day_39: LinearLayout
    internal lateinit var day_40: LinearLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater!!.inflate(R.layout.fragment_tab_month, container, false)
        initUiElements()
        dayOfWeek_firstDayOfMonth()
        calendarData()
        putViews()
        return view
    }

    private fun initUiElements() {

        day_0 = view.findViewById(R.id.day_0) as LinearLayout
        day_1 = view.findViewById(R.id.day_1) as LinearLayout
        day_2 = view.findViewById(R.id.day_2) as LinearLayout
        day_3 = view.findViewById(R.id.day_3) as LinearLayout
        day_4 = view.findViewById(R.id.day_4) as LinearLayout
        day_5 = view.findViewById(R.id.day_5) as LinearLayout
        day_6 = view.findViewById(R.id.day_6) as LinearLayout
        day_7 = view.findViewById(R.id.day_7) as LinearLayout
        day_8 = view.findViewById(R.id.day_8) as LinearLayout
        day_9 = view.findViewById(R.id.day_9) as LinearLayout
        day_10 = view.findViewById(R.id.day_10) as LinearLayout
        day_11 = view.findViewById(R.id.day_11) as LinearLayout
        day_12 = view.findViewById(R.id.day_12) as LinearLayout
        day_13 = view.findViewById(R.id.day_13) as LinearLayout
        day_14 = view.findViewById(R.id.day_14) as LinearLayout
        day_15 = view.findViewById(R.id.day_15) as LinearLayout
        day_16 = view.findViewById(R.id.day_16) as LinearLayout
        day_17 = view.findViewById(R.id.day_17) as LinearLayout
        day_18 = view.findViewById(R.id.day_18) as LinearLayout
        day_19 = view.findViewById(R.id.day_19) as LinearLayout
        day_20 = view.findViewById(R.id.day_20) as LinearLayout
        day_21 = view.findViewById(R.id.day_21) as LinearLayout
        day_22 = view.findViewById(R.id.day_22) as LinearLayout
        day_23 = view.findViewById(R.id.day_23) as LinearLayout
        day_24 = view.findViewById(R.id.day_24) as LinearLayout
        day_25 = view.findViewById(R.id.day_25) as LinearLayout
        day_26 = view.findViewById(R.id.day_26) as LinearLayout
        day_27 = view.findViewById(R.id.day_27) as LinearLayout
        day_28 = view.findViewById(R.id.day_28) as LinearLayout
        day_29 = view.findViewById(R.id.day_29) as LinearLayout
        day_30 = view.findViewById(R.id.day_30) as LinearLayout
        day_31 = view.findViewById(R.id.day_31) as LinearLayout
        day_32 = view.findViewById(R.id.day_32) as LinearLayout
        day_33 = view.findViewById(R.id.day_33) as LinearLayout
        day_34 = view.findViewById(R.id.day_34) as LinearLayout
        day_35 = view.findViewById(R.id.day_35) as LinearLayout
        day_36 = view.findViewById(R.id.day_36) as LinearLayout
        day_37 = view.findViewById(R.id.day_37) as LinearLayout
        day_38 = view.findViewById(R.id.day_38) as LinearLayout
        day_39 = view.findViewById(R.id.day_39) as LinearLayout
        day_40 = view.findViewById(R.id.day_40) as LinearLayout
    }

    private fun putViews() {

        for (i in 1..numofDays) {
            val date: TextView = TextView(context)


            // Creating a LinearLayout.LayoutParams object for text view
            var params: LayoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT, // This will define text view width
                LayoutParams.WRAP_CONTENT
            )

            date.setPadding(0, 10, 0, 0);

            // Now, specify the text view width and height (dimension)
            date.layoutParams = params
         

            // Display some text on the newly created text view
            date.text = i.toString()

            when (dayOfWeek_firstDayOfMonth - 1) {

                0 -> {
                    day_0.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                1 -> {
                    day_1.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                2 -> {
                    day_2.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                3 -> {
                    day_3.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                4 -> {
                    day_4.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                5 -> {
                    day_5.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                6 -> {
                    day_6.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                7 -> {
                    day_7.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                8 -> {
                    day_8.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                9 -> {
                    day_9.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                10 -> {
                    day_10.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                11 -> {
                    day_11.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                12 -> {
                    day_12.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                13 -> {
                    day_13.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                14 -> {
                    day_14.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                15 -> {
                    day_15.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                16 -> {
                    day_16.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                17 -> {
                    day_17.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                18 -> {
                    day_18.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                19 -> {
                    day_19.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                20 -> {
                    day_20.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                21 -> {
                    day_21.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                22 -> {
                    day_22.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                23 -> {
                    day_23.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                24 -> {
                    day_24.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                25 -> {
                    day_25.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                26 -> {
                    day_26.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                27 -> {
                    day_27.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                28 -> {
                    day_28.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                29 -> {
                    day_29.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                30 -> {
                    day_30.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                31 -> {
                    day_31.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                32 -> {
                    day_32.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                33 -> {
                    day_33.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                34 -> {
                    day_34.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                35 -> {
                    day_35.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                36 -> {
                    day_36.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                36 -> {
                    day_36.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                37 -> {
                    day_37.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                38 -> {
                    day_38.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                39 -> {
                    day_39.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
                40 -> {
                    day_40.addView(date)
                    dayOfWeek_firstDayOfMonth++
                }
            }
            /*if (day_40) {
                day_2.addView(date)
            }*/
        }

    }


    fun dayOfWeek_firstDayOfMonth(): Any? {
        val c = Calendar.getInstance()
        c.set(Calendar.DAY_OF_MONTH, 1)
        dayOfWeek_firstDayOfMonth = c.get(Calendar.DAY_OF_WEEK)
        return null
    }

    fun calendarData(): Any? {
        val calendar = Calendar.getInstance()
        //Current Month
        monthAsInt = calendar.get(Calendar.MONTH)
        //Current Year
        yearAsInt = calendar.get(Calendar.YEAR)
        //Current Day
        dayAsInt = calendar.get(Calendar.DAY_OF_MONTH)
        //Total no of days in Current Month
        numofDays = calendar.getActualMaximum(Calendar.DATE)
        println("num of days" + numofDays)
        return null
    }

}