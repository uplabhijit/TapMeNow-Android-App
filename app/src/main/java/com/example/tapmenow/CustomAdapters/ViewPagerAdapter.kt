package com.example.tapmenow.CustomAdapters


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.tapmenow.Fragments.DayFragment
import com.example.tapmenow.Fragments.MonthFragment
import com.example.tapmenow.Fragments.WeekFragment

class ViewPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return MonthFragment()
            1 -> return WeekFragment()
            else -> return DayFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}
