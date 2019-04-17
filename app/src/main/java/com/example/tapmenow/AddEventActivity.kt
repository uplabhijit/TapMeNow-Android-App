package com.example.tapmenow

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import kotlinx.android.synthetic.main.activity_addevent.*
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class AddEventActivity : AppCompatActivity() {
    var yearfinal: Int = 0
    var monthfinal: Int = 0
    var dayfinal: Int = 0
    var hourfinal: Int = 0
    var minutefinal: Int = 0
    var startTime: Long = 0//picked up value in formatted way
    var endTime: Long = 0//picked up value in formatted way
    var id: Int = 0
    var _12hrtime: String = ""
    var spinnerArray = arrayOf("Dumbell", "Punching Bag", "Yoga Ball")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addevent)
        initUiElement()
        onTap()
        val spinnerArrayAdapter =
            ArrayAdapter<String>(this, R.layout.spinnerrow, R.id.weekofday, spinnerArray)

        //selected item will look like a spinner set from XML

        date_spinner.setAdapter(spinnerArrayAdapter)
        notification_timer_spinner.setAdapter(spinnerArrayAdapter)
        repeat_spinner.setAdapter(spinnerArrayAdapter)

    }

    private fun onTap() {
        btn_cancel.setOnClickListener { finish() }
        rl_cross.setOnClickListener { finish() }
        rl_datetime.setOnClickListener {
            id = it.getId()
            println(id)
            openDatePickerDialog()
        }
        rl_datetimeend.setOnClickListener {
            id = it.getId()

            openDatePickerDialog()
        }
        date_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

        }
        notification_timer_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

        }
        repeat_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

        }


    }

    private fun openDatePickerDialog() {
        println("id" + id)
        val cal = Calendar.getInstance()
        DatePickerDialog(
            this@AddEventActivity, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun initUiElement() {
        val btn_cancel = findViewById(R.id.btn_cancel) as Button


    }

    val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

        var yearfinal = year
        var monthfinal = monthOfYear
        var dayfinal = dayOfMonth

        if (monthOfYear < 10) {

            monthfinal = ("0$monthOfYear").toInt()
        }
        if (dayOfMonth < 10) {

            dayfinal = ("0$dayOfMonth").toInt()
        }

        if (id == 2131230917) {
            txt_datepicked.text = getMonth(monthfinal) + " " + dayfinal + " " + yearfinal
        } else if (id == 2131230923) {
            txt_enddatepicked.text = getMonth(monthfinal) + " " + dayfinal + " " + yearfinal
        }


        //launching time pciker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(this, timeSetListener, hour, minute, false)

        tpd.show()

    }

    var timeSetListener = TimePickerDialog.OnTimeSetListener { view, h, m ->
        hourfinal = h
        minutefinal = m
        getMilisecond()
        if (id == 2131230917) {
            txt_timepicked.setText(get12hrTime(hourfinal, minutefinal))
        } else if (id == 2131230923) {
            txt_endtimepicked.setText(get12hrTime(hourfinal, minutefinal))
        }


        dayfinal = 0
        monthfinal = 0
        yearfinal = 0
        hourfinal = 0
        minutefinal = 0

    }


    fun getMonth(month: Int): String {
        return DateFormatSymbols().months[month]
    }

    fun getMilisecond() {
        val calendar = Calendar.getInstance()
        calendar.set(
            yearfinal, monthfinal, dayfinal,
            hourfinal, minutefinal, 0
        )
        if (id == 2131230917) {
            startTime = calendar.timeInMillis
        } else if (id == 2131230923) {
            endTime = calendar.timeInMillis
        }

    }

    fun get12hrTime(hr: Int, tm: Int): String {

        try {
            val _24HourTime = "$hr:$tm"
            val _24HourSDF = SimpleDateFormat("HH:mm")
            val _12HourSDF = SimpleDateFormat("hh:mm a")
            val _24HourDt = _24HourSDF.parse(_24HourTime)
            println(_24HourDt)
            _12hrtime = _12HourSDF.format(_24HourDt)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return _12hrtime
    }
}