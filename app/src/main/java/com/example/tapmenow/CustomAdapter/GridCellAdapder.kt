package com.example.tapmenow.CustomAdapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.tapmenow.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


// Inner Class
class GridCellAdapter
    (
    private val _context: Context,
    textViewResourceId: Int,
    private val month: Int,
    private val year: Int


) :
    BaseAdapter(),
    View.OnClickListener {
    var DAY_OFFSET: Int = 1
    private val list: MutableList<String>
    private val weekdays = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    private val months = arrayOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

    private val daysOfMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    private var daysInMonth: Int = 0
    private val prevMonthDays: Int = 0
    var currentDayOfMonth: Int = 0
        private set
    var currentWeekDay: Int = 0
    private var gridcell: TextView? = null
    private var root_rl: RelativeLayout? = null
    private var gridcell_dayofweek: TextView? = null
    private var ll_griditem: LinearLayout? = null

    private var num_events_per_day: TextView? = null
    private val eventsPerMonthMap: HashMap<*, *>?
    private val dateFormatter = SimpleDateFormat("dd-MMM-yyyy")

    init {
        this.list = ArrayList()

        Log.d("TAG", "==> Passed in Date FOR Month: $month Year: $year")
        val calendar = Calendar.getInstance()
        currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        currentWeekDay = calendar.get(Calendar.DAY_OF_WEEK)
        Log.d("TAG", "New Calendar:= " + calendar.time.toString())
        Log.d("TAG", "CurrentDayOfWeek :$currentWeekDay")
        Log.d("TAG", "CurrentDayOfMonth :$currentDayOfMonth")

        // Print Month
        printMonth(month, year)

        // Find Number of Events
        eventsPerMonthMap = findNumberOfEventsPerMonth(year, month)
    }

    private fun getMonthAsString(i: Int): String {
        return months[i]
    }

    private fun getWeekDayAsString(i: Int): String {
        return weekdays[i]
    }

    private fun getNumberOfDaysOfMonth(i: Int): Int {
        return daysOfMonth[i]
    }

    override fun getItem(position: Int): String {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

    /**
     * Prints Month
     *
     * @param mm
     * @param yy
     */
    private fun printMonth(mm: Int, yy: Int) {
        Log.d("TAG", "==> printMonth: mm: $mm yy: $yy")
        // The number of days to leave blank at
        // the start of this month.
        var trailingSpaces = 0
        val leadSpaces = 0
        var daysInPrevMonth = 0
        var prevMonth = 0
        var prevYear = 0
        var nextMonth = 0
        var nextYear = 0

        val currentMonth = mm - 1
        println("currentmonth :$mm")
        val currentMonthName = getMonthAsString(currentMonth)
        daysInMonth = getNumberOfDaysOfMonth(currentMonth)

        Log.d("TAG", "Current Month:  $currentMonthName having $daysInMonth days.")

        // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
        val cal = GregorianCalendar(yy, currentMonth, 1)
        Log.d("TAG", "Gregorian Calendar:= " + cal.time.toString())

        if (currentMonth == 11) {
            prevMonth = currentMonth - 1
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth)
            nextMonth = 0
            prevYear = yy
            nextYear = yy + 1
            Log.d("TAG", "*->PrevYear: $prevYear PrevMonth:$prevMonth NextMonth: $nextMonth NextYear: $nextYear")
        } else if (currentMonth == 0) {
            prevMonth = 11
            prevYear = yy - 1
            nextYear = yy
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth)
            nextMonth = 1
            Log.d("TAG", "**--> PrevYear: $prevYear PrevMonth:$prevMonth NextMonth: $nextMonth NextYear: $nextYear")
        } else {
            prevMonth = currentMonth - 1
            nextMonth = currentMonth + 1
            nextYear = yy
            prevYear = yy
            daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth)
            Log.d("TAG", "***---> PrevYear: $prevYear PrevMonth:$prevMonth NextMonth: $nextMonth NextYear: $nextYear")
        }

        // Compute how much to leave before before the first day of the
        // month.
        // getDay() returns 0 for Sunday.
        val currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1
        trailingSpaces = currentWeekDay

        Log.d("TAG", "Week Day:" + currentWeekDay + " is " + getWeekDayAsString(currentWeekDay))
        Log.d("TAG", "No. Trailing space to Add: $trailingSpaces")
        Log.d("TAG", "No. of Days in Previous Month: $daysInPrevMonth")

        if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1) {
            ++daysInMonth
        }

        // Trailing Month days
        for (i in 0 until trailingSpaces) {
            Log.d(
                "TAG",
                "PREV MONTH:= " + prevMonth + " => " + getMonthAsString(prevMonth) + " " + (daysInPrevMonth - trailingSpaces + DAY_OFFSET + i).toString()
            )
            list.add(
                (daysInPrevMonth - trailingSpaces + DAY_OFFSET + i).toString() + "-GREY" + "-" + getMonthAsString(
                    prevMonth
                ) + "-" + prevYear
            )
        }

        // Current Month Days
        for (i in 1..daysInMonth) {
            println("ii" + i)
            Log.d(currentMonthName, i.toString() + " " + getMonthAsString(currentMonth) + " " + yy)
            if (i == currentDayOfMonth) {
                list.add(i.toString() + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy)
            } else {
                list.add(i.toString() + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy)
            }
        }

        // Leading Month days
        for (i in 1..42 - (list.size)) {
            println("list_size" + list.size)
            Log.d("TAG", "NEXT MONTH:= " + getMonthAsString(nextMonth))
            list.add((i).toString() + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear)
            println("i ${i}")
        }
    }

    /**
     * NOTE: YOU NEED TO IMPLEMENT THIS PART Given the YEAR, MONTH, retrieve
     * ALL entries from a SQLite database for that month. Iterate over the
     * List of All entries, and get the dateCreated, which is converted into
     * day.
     *
     * @param year
     * @param month
     * @return
     */
    private fun findNumberOfEventsPerMonth(year: Int, month: Int): HashMap<*, *> {
// DateFormat dateFormatter2 = new DateFormat();
        //
        // String day = dateFormatter2.format("dd", dateCreated).toString();
        //
        // if (map.containsKey(day))
        // {
        // Integer val = (Integer) map.get(day) + 1;
        // map.put(day, val);
        // }
        // else
        // {
        // map.put(day, 1);
        // }
        return HashMap<String, Int>()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val height = parent.measuredHeight
        println("ADAPTERPARENT" + height)
        var row: View? = convertView
        if (row == null) {
            val inflater = _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            row = inflater.inflate(R.layout.grid_item, parent, false)
        }

        // Get a reference to the Day gridcell
        gridcell = row!!.findViewById(R.id.calendar_day_gridcell) as TextView
        root_rl = row!!.findViewById(R.id.root_rl) as RelativeLayout
        root_rl!!.getLayoutParams().height = (height) / 6;
        gridcell_dayofweek = row.findViewById(R.id.dayofweek) as TextView
        ll_griditem = row.findViewById(R.id.ll_griditem) as LinearLayout
        gridcell!!.setOnClickListener(this)

        // ACCOUNT FOR SPACING

        Log.d("TAG", "Current Day: $currentDayOfMonth")
        val day_color = list[position].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val theday = day_color[0]

        val themonth = day_color[2]
        val theyear = day_color[3]
        if (!eventsPerMonthMap!!.isEmpty() && eventsPerMonthMap != null) {
            if (eventsPerMonthMap.containsKey(theday)) {
                // num_events_per_day = (TextView) row.findViewById(R.id.num_events_per_day);
                val numEvents = eventsPerMonthMap[theday] as Int?
                //  num_events_per_day.setText(numEvents.toString());
            }
        }
        println("position $position")
        // Set the Day GridCell
        when (position) {
            0 -> {
                gridcell_dayofweek!!.text = "S"
                gridcell_dayofweek!!.visibility = View.VISIBLE
                gridcell!!.text = theday
                gridcell!!.tag = "$theday-$themonth-$theyear"
            }
            1 -> {
                gridcell_dayofweek!!.text = "M"
                gridcell_dayofweek!!.visibility = View.VISIBLE
                gridcell!!.text = theday
                gridcell!!.tag = "$theday-$themonth-$theyear"
            }
            2 -> {
                gridcell_dayofweek!!.text = "T"
                gridcell_dayofweek!!.visibility = View.VISIBLE
                gridcell!!.text = theday
                gridcell!!.tag = "$theday-$themonth-$theyear"
            }
            3 -> {
                gridcell_dayofweek!!.text = "W"
                gridcell_dayofweek!!.visibility = View.VISIBLE
                gridcell!!.text = theday
                gridcell!!.tag = "$theday-$themonth-$theyear"
            }
            4 -> {
                gridcell_dayofweek!!.text = "T"
                gridcell_dayofweek!!.visibility = View.VISIBLE
                gridcell!!.text = theday
                gridcell!!.tag = "$theday-$themonth-$theyear"
            }
            5 -> {
                gridcell_dayofweek!!.text = "F"
                gridcell_dayofweek!!.visibility = View.VISIBLE
                gridcell!!.text = theday
                gridcell!!.tag = "$theday-$themonth-$theyear"
            }
            6 -> {
                gridcell_dayofweek!!.text = "S"
                gridcell_dayofweek!!.visibility = View.VISIBLE
                gridcell!!.text = theday
                gridcell!!.tag = "$theday-$themonth-$theyear"
            }
            else -> {
                gridcell!!.text = theday
                gridcell!!.tag = "$theday-$themonth-$theyear"
            }
        }


        /////////////////////////////////////////////////////
        /*if (position == 2) {
            ll_griditem!!.removeAllViews()
            for (i in 1..2) {
                //ll_griditem!!.visibility = View.GONE
                val lparams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
                )
                val tv = TextView(this._context)
                tv.layoutParams = lparams

                tv.setBackgroundColor(Color.parseColor("#74B9FF"))
                if (i == 1) {

                    tv.text = "Birthday"
                    tv.setBackgroundColor(Color.parseColor("#74B9FF"))
                    tv.setTextColor(Color.parseColor("#0A79DF"))
                }
                if (i == 2) {

                    tv.text = "Greet"
                    tv.setBackgroundColor(Color.parseColor("#F9DDA4"))
                    tv.setTextColor(Color.parseColor("#DFAF2B"))
                }
                tv.setSingleLine()

                this.ll_griditem!!.addView(tv)
            }
        }
        */
        // /////////////////////////////////////////////////////////
        Log.d("TAG", "Setting GridCell $theday-$themonth-$theyear")

        if (day_color[1] == "GREY") {
            gridcell!!.setTextColor(Color.LTGRAY)
        }
        if (day_color[1] == "WHITE") {
            gridcell!!.setTextColor(Color.BLACK)
        }
        if (day_color[1] == "BLUE") {
            gridcell!!.setTextColor(Color.parseColor("#000000"))
        }
        return row
    }

    override fun onClick(view: View) {
        val date_month_year = view.tag as String
        //            selectedDayMonthYearButton.setText("Selected: " + date_month_year);

        try {
            val parsedDate = dateFormatter.parse(date_month_year)
            Log.d("TAG", "Parsed Date: $parsedDate")

        } catch (e: ParseException) {
            e.printStackTrace()
        }

    }


}
