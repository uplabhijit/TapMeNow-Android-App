package com.example.tapmenow

import android.graphics.RectF
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.TypedValue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.alamkanak.weekview.DateTimeInterpreter
import com.alamkanak.weekview.MonthLoader
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewEvent
import java.text.SimpleDateFormat
import java.util.*


class DayFragment : Fragment(), WeekView.EventClickListener, MonthLoader.MonthChangeListener,
    WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {
    private val TYPE_DAY_VIEW: Int = 1
    private val TYPE_THREE_DAY_VIEW: Int = 2
    private val TYPE_WEEK_VIEW: Int = 3
    private var mWeekViewType: Int = TYPE_THREE_DAY_VIEW
    private lateinit var mWeekView: WeekView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_tab_day, container, false)
        mWeekView = view.findViewById(R.id.weekView) as WeekView

        mWeekViewType = TYPE_DAY_VIEW
        mWeekView.numberOfVisibleDays = 1
        // Lets change some dimensions to best fit the view.
        mWeekView.columnGap =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics).toInt()
        mWeekView.textSize =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics).toInt()
        mWeekView.eventTextSize =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics).toInt()
        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this)

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this)

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this)

        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this)

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false)

        return view
    }

    private fun setupDateTimeInterpreter(shortDate: Boolean) {
        mWeekView.dateTimeInterpreter = object : DateTimeInterpreter {
            override fun interpretDate(date: Calendar): String {
                val weekdayNameFormat = SimpleDateFormat("EEE", Locale.getDefault())
                var weekday = weekdayNameFormat.format(date.time)
                val format = SimpleDateFormat(" M/d", Locale.getDefault())

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = weekday[0].toString()
                return weekday.toUpperCase() + format.format(date.time)
            }

            override fun interpretTime(hour: Int): String {
                return if (hour > 11) (hour - 12).toString() + " PM" else if (hour == 0) "12 AM" else "$hour AM"
            }
        }
    }

    protected fun getEventTitle(time: Calendar): String {
        return String.format(
            "Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(
                Calendar.MONTH
            ) + 1, time.get(Calendar.DAY_OF_MONTH)
        )
    }

    override fun onEventClick(event: WeekViewEvent, eventRect: RectF) {
        Toast.makeText(context, "Clicked " + event.name, Toast.LENGTH_SHORT).show()
    }

    override fun onEventLongPress(event: WeekViewEvent, eventRect: RectF) {
        Toast.makeText(context, "Long pressed event: " + event.name, Toast.LENGTH_SHORT).show()
    }

    override fun onEmptyViewLongPress(time: Calendar) {
        Toast.makeText(context, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show()
    }

    fun getWeekView(): WeekView {
        return mWeekView
    }

    override fun onMonthChange(newYear: Int, newMonth: Int): List<WeekViewEvent> {
        val events = ArrayList<WeekViewEvent>()

        var startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 0)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        var endTime = startTime.clone() as Calendar
        endTime.set(Calendar.HOUR_OF_DAY, 24)
        endTime.set(Calendar.MONTH, newMonth - 1)
        var event = WeekViewEvent(1, getEventTitle(startTime), startTime, endTime)
        event.color = resources.getColor(R.color.colorPrimary)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 6)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.HOUR_OF_DAY, 10)
        endTime.set(Calendar.MINUTE, 0)
        event = WeekViewEvent(2, getEventTitle(startTime), startTime, endTime)
        event.color = resources.getColor(R.color.grey)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 11)
        startTime.set(Calendar.MINUTE, 20)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.HOUR_OF_DAY, 12)
        endTime.set(Calendar.MINUTE, 0)
        event = WeekViewEvent(3, getEventTitle(startTime), startTime, endTime)
        event.color = resources.getColor(R.color.colorAccent)
        events.add(event)


        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 13)
        startTime.set(Calendar.MINUTE, 20)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.HOUR_OF_DAY, 16)
        endTime.set(Calendar.MINUTE, 0)
        event = WeekViewEvent(4, getEventTitle(startTime), startTime, endTime)
        event.color = resources.getColor(R.color.colorPrimaryDark)
        events.add(event)

        return events
    }

}