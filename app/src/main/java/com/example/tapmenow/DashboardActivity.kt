package com.example.tapmenow

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.example.tapmenow.CustomAdapters.ViewPagerAdapter
import com.example.tapmenow.FragmentActivity.CommonActivity
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.app_bar_dashboard.*
import kotlinx.android.synthetic.main.content_dashboard.*
import java.text.SimpleDateFormat
import java.util.*


class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var shouldShow: Boolean = false
    internal var dateFormatForMonth = SimpleDateFormat("MMM , yyyy", Locale.getDefault())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        //Method to Configure Tab LAyout[configureTabLayout()]
        configureTabLayout()
        onTap()
        //Setting the Current date in Appbar
        setDateToolbar()

        //Calender View for AppBar
        compactcalendar_view.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                val events = compactcalendar_view.getEvents(dateClicked)
                //Setting ViewPager on Day Tab
                viewPager.currentItem = 2
                println("Day was clicked: $dateClicked with events $events")
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                Log.d("MONTHSCROLL", "Month was scrolled to: $firstDayOfNewMonth")
                setDateToolbar()
            }
        })


    }

    fun setDateToolbar(month: Int? = null, year: String? = null) {

        if (month == null && year == null) {

            txt_date.text = dateFormatForMonth.format(compactcalendar_view.firstDayOfCurrentMonth)
        } else {
            println("monthfromfragment" + month)
            when (month) {

                1 -> {

                    txt_date.text = "Jan, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(1, year))

                }
                2 -> {
                    txt_date.text = "Feb, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(2, year))
                }
                3 -> {
                    txt_date.text = "Mar, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(3, year))
                }
                4 -> {
                    txt_date.text = "Apr, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(4, year))
                }
                5 -> {
                    txt_date.text = "May, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(5, year))
                }
                6 -> {
                    txt_date.text = "Jun, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(6, year))
                }
                7 -> {
                    txt_date.text = "Jul, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(7, year))
                }
                8 -> {
                    txt_date.text = "Aug, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(8, year))
                }

                9 -> {
                    txt_date.text = "Sep, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(9, year))
                }
                10 -> {
                    txt_date.text = "Oct, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(10, year))
                }
                11 -> {
                    txt_date.text = "Nov, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(11, year))
                }
                12 -> {
                    txt_date.text = "Dec, $year"
                    val calendar = Calendar.getInstance()
                    compactcalendar_view.setCurrentDate(getMiliseconds(12, year))
                }
            }


        }
    }

    //get miliseconds based on month,year &day
    private fun getMiliseconds(month: Int, year: String?): Date? {


        val calendar = Calendar.getInstance()
        calendar.set(
            year!!.toInt(), month - 1, 1, 0, 0, 0
        )
        println("calendar.timeInMillis" + calendar.timeInMillis + " " + month + " " + year)
        return Date(calendar.timeInMillis)
    }


    private fun onTap() {
        rl_addEvent.setOnClickListener {
            val intent = Intent(this@DashboardActivity, AddEventActivity::class.java)
            startActivity(intent)

        }
        rl_toolbardate.setOnClickListener {
            if (!shouldShow) {
                compactcalendar_view.visibility = View.VISIBLE
                shouldShow = true
                dropdownicon.setBackgroundResource(R.drawable.dropuparrow)
            } else {
                compactcalendar_view.visibility = View.GONE
                shouldShow = false
                dropdownicon.setBackgroundResource(R.drawable.dropdownarrow)
            }
        }
    }

    //Method to Configure Tab LAyout
    private fun configureTabLayout() {
        tab_layout.addTab(tab_layout.newTab().setText("Month"))
        tab_layout.addTab(tab_layout.newTab().setText("Week"))
        tab_layout.addTab(tab_layout.newTab().setText("Day"))


        val adapter = ViewPagerAdapter(
            supportFragmentManager,
            tab_layout.tabCount
        )
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tab_layout)
        )
        tab_layout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_mycalenders -> {
                val intent = Intent(
                    this@DashboardActivity, CommonActivity
                    ::class.java
                )
                intent.putExtra("Title", "nav_mycalenders")
                startActivity(intent);
            }
            R.id.nav_upcommingtaps -> {
                val intent = Intent(
                    this@DashboardActivity, CommonActivity
                    ::class.java
                )
                intent.putExtra("Title", "nav_upcommingtaps")
                startActivity(intent);
            }
            R.id.nav_library -> {
                val intent = Intent(
                    this@DashboardActivity, CommonActivity
                    ::class.java
                )
                intent.putExtra("Title", "nav_library")
                startActivity(intent);
            }
            R.id.nav_myaccount -> {
                val intent = Intent(
                    this@DashboardActivity, CommonActivity
                    ::class.java
                )
                intent.putExtra("Title", "nav_myaccount")
                startActivity(intent);
            }
            R.id.nav_notifications -> {
                val intent = Intent(
                    this@DashboardActivity, CommonActivity
                    ::class.java
                )
                intent.putExtra("Title", "nav_notifications")
                startActivity(intent);
            }
            R.id.nav_signout -> {
                val intent = Intent(
                    this@DashboardActivity, CommonActivity
                    ::class.java
                )
                intent.putExtra("Title", "nav_signout")
                startActivity(intent);
            }

        }


        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
