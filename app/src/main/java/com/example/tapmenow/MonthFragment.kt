package com.example.tapmenow


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 */


class MonthFragment : Fragment(), View.OnClickListener {

    /* var dayOfWeek_firstDayOfMonth: Int = 0
     var monthAsInt: Int = 0
     var yearAsInt: Int = 0
     var dayAsInt: Int = 0
     var numofDays: Int = 0
 */
    internal lateinit var view: View
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
        view = inflater!!.inflate(R.layout.fragment_tab_month, container, false)
        initUiElements()

        /*initUiElements()
        calendarData()
        getMonthDays(monthAsInt + 1, yearAsInt)//month+1 is for current month //change month here
        dayOfWeek(monthAsInt, yearAsInt)//change month here
        putViews()
        println("numofDays :" + numofDays)
        println("monthAsInt :" + monthAsInt)
        println("yearAsInt :" + yearAsInt)
        println("dayAsInt :" + dayAsInt)
        println("dayOfWeek_firstDayOfMonth :" + dayOfWeek_firstDayOfMonth)
*/
        return view
    }

    private fun initUiElements() {
        _calendar = Calendar.getInstance(Locale.getDefault())
        month = _calendar.get(Calendar.MONTH) + 1
        year = _calendar.get(Calendar.YEAR)
        prevMonth = view.findViewById<View>(R.id.prevMonth) as ImageView
        prevMonth.setOnClickListener(this)
        _calendar = Calendar.getInstance(Locale.getDefault());
        currentMonth = view.findViewById<View>(R.id.currentMonth) as TextView
        currentMonth.text = DateFormat.format(dateTemplate, _calendar.getTime())

        nextMonth = view.findViewById<View>(R.id.nextMonth) as ImageView
        nextMonth.setOnClickListener(this)

        calendarView = view.findViewById<View>(R.id.calendar) as GridView

        // Initialised
        adapter = GridCellAdapter(this!!.context!!, R.id.calendar_day_gridcell, month, year)
        adapter.notifyDataSetChanged()
        calendarView.adapter = adapter
    }

    /**
     * @param month
     * @param year
     */
    private fun setGridCellAdapterToDate(month: Int, year: Int) {
        adapter = GridCellAdapter(this!!.context!!, R.id.calendar_day_gridcell, month, year)
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

    /*private fun putViews() {
        val gridview = view.findViewById(R.id.gridView) as GridView
        var adapter: MonthViewAdapter? = null
        var datesList = ArrayList<Dates>()


        // load dates
        for (i in 1..numofDays) {

            datesList.add(Dates(i))


        }


        gridview.adapter = MonthViewAdapter(this.activity!!, datesList)


    }*/


    /* fun calendarData(): Any? {
         val calendar = Calendar.getInstance()
         //Current Month //starts from 0
         monthAsInt = calendar.get(Calendar.MONTH)
         //Current Year
         yearAsInt = calendar.get(Calendar.YEAR)
         //Current Day
         dayAsInt = calendar.get(Calendar.DAY_OF_MONTH)
         //Total no of days in Current Month

         return null

     }*/

    /*  fun getMonthDays(month: Int, year: Int): Int {

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
*/

/*

      fun dayOfWeek(month: Int, year: Int): String {

          val calendar = Calendar.getInstance()
          calendar.set(Calendar.DAY_OF_MONTH, 1);
          calendar.set(Calendar.MONTH, month);
          calendar.set(Calendar.YEAR, year);
          dayOfWeek_firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)
          return dayOfWeek_firstDayOfMonth.toString()
      }
*/


    // Inner Class
    inner class GridCellAdapter
        (private val _context: Context, textViewResourceId: Int, private val month: Int, private val year: Int) :
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
        private val num_events_per_day: TextView? = null
        private val eventsPerMonthMap: HashMap<*, *>?
        private val dateFormatter = SimpleDateFormat("dd-MMM-yyyy")

        init {
            this.list = ArrayList()

            Log.d(tag, "==> Passed in Date FOR Month: $month Year: $year")
            val calendar = Calendar.getInstance()
            currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            currentWeekDay = calendar.get(Calendar.DAY_OF_WEEK)
            Log.d(tag, "New Calendar:= " + calendar.time.toString())
            Log.d(tag, "CurrentDayOfWeek :$currentWeekDay")
            Log.d(tag, "CurrentDayOfMonth :$currentDayOfMonth")

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
            Log.d(tag, "==> printMonth: mm: $mm yy: $yy")
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

            Log.d(tag, "Current Month:  $currentMonthName having $daysInMonth days.")

            // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
            val cal = GregorianCalendar(yy, currentMonth, 1)
            Log.d(tag, "Gregorian Calendar:= " + cal.time.toString())

            if (currentMonth == 11) {
                prevMonth = currentMonth - 1
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth)
                nextMonth = 0
                prevYear = yy
                nextYear = yy + 1
                Log.d(tag, "*->PrevYear: $prevYear PrevMonth:$prevMonth NextMonth: $nextMonth NextYear: $nextYear")
            } else if (currentMonth == 0) {
                prevMonth = 11
                prevYear = yy - 1
                nextYear = yy
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth)
                nextMonth = 1
                Log.d(tag, "**--> PrevYear: $prevYear PrevMonth:$prevMonth NextMonth: $nextMonth NextYear: $nextYear")
            } else {
                prevMonth = currentMonth - 1
                nextMonth = currentMonth + 1
                nextYear = yy
                prevYear = yy
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth)
                Log.d(tag, "***---> PrevYear: $prevYear PrevMonth:$prevMonth NextMonth: $nextMonth NextYear: $nextYear")
            }

            // Compute how much to leave before before the first day of the
            // month.
            // getDay() returns 0 for Sunday.
            val currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1
            trailingSpaces = currentWeekDay

            Log.d(tag, "Week Day:" + currentWeekDay + " is " + getWeekDayAsString(currentWeekDay))
            Log.d(tag, "No. Trailing space to Add: $trailingSpaces")
            Log.d(tag, "No. of Days in Previous Month: $daysInPrevMonth")

            if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1) {
                ++daysInMonth
            }

            // Trailing Month days
            for (i in 0 until trailingSpaces) {
                Log.d(
                    tag,
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
                Log.d(currentMonthName, i.toString() + " " + getMonthAsString(currentMonth) + " " + yy)
                if (i == currentDayOfMonth) {
                    list.add(i.toString() + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy)
                } else {
                    list.add(i.toString() + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy)
                }
            }

            // Leading Month days
            for (i in 0 until list.size % 7) {
                Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth))
                list.add((i + 1).toString() + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear)
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
            var row: View? = convertView
            if (row == null) {
                val inflater = _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                row = inflater.inflate(R.layout.grid_item, parent, false)
            }

            // Get a reference to the Day gridcell
            gridcell = row!!.findViewById(R.id.calendar_day_gridcell) as TextView
            gridcell!!.setOnClickListener(this)

            // ACCOUNT FOR SPACING

            Log.d(tag, "Current Day: $currentDayOfMonth")
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

            // Set the Day GridCell
            gridcell!!.text = theday
            gridcell!!.tag = "$theday-$themonth-$theyear"
            Log.d(tag, "Setting GridCell $theday-$themonth-$theyear")

            if (day_color[1] == "GREY") {
                gridcell!!.setTextColor(Color.LTGRAY)
            }
            if (day_color[1] == "WHITE") {
                gridcell!!.setTextColor(Color.BLACK)
            }
            if (day_color[1] == "BLUE") {
                gridcell!!.setTextColor(resources.getColor(R.color.black))
            }
            return row
        }

        override fun onClick(view: View) {
            val date_month_year = view.tag as String
            //            selectedDayMonthYearButton.setText("Selected: " + date_month_year);

            try {
                val parsedDate = dateFormatter.parse(date_month_year)
                Log.d(tag, "Parsed Date: $parsedDate")

            } catch (e: ParseException) {
                e.printStackTrace()
            }

        }


    }


}

