package com.example.tapmenow.FragmentActivity

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.FragmentActivity
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.example.tapmenow.Fragments.MyCalendersFragment
import com.example.tapmenow.Fragments.UpCommingTapsFragment
import com.example.tapmenow.R


class CommonActivity : FragmentActivity() {
    internal lateinit var frameLayout: FrameLayout


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)


        frameLayout = findViewById<View>(R.id.framelayout) as FrameLayout
        if (intent.getStringExtra("Title") != null && intent.getStringExtra("Title") == "nav_mycalenders") {

            val myToast = Toast.makeText(this@CommonActivity, "nav_mycalenders tapped", Toast.LENGTH_SHORT)
            myToast.setGravity(Gravity.LEFT, 200, 200)
            myToast.show()


        }
        if (intent.getStringExtra("Title") != null && intent.getStringExtra("Title") == "nav_upcommingtaps") {

            val myToast = Toast.makeText(this@CommonActivity, "nav_upcommingtaps tapped", Toast.LENGTH_SHORT)
            myToast.setGravity(Gravity.LEFT, 200, 200)
            myToast.show()


        }
        if (intent.getStringExtra("Title") != null && intent.getStringExtra("Title") == "nav_library") {


            /* val fragmentManager = supportFragmentManager
             fragmentManager.beginTransaction().replace(R.id.framelayout, AllDrawerItemsActivity()).commit()*/
        }
        if (intent.getStringExtra("Title") != null && intent.getStringExtra("Title") == "nav_myaccount") {


/*            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.framelayout, AllDrawerItemsActivity()).commit()*/
        }
        if (intent.getStringExtra("Title") != null && intent.getStringExtra("Title") == "nav_notifications") {


            /*  val fragmentManager = supportFragmentManager
              fragmentManager.beginTransaction().replace(R.id.framelayout, AllDrawerItemsActivity()).commit()*/
        }
        if (intent.getStringExtra("Title") != null && intent.getStringExtra("Title") == "nav_signout") {


            /* val fragmentManager = supportFragmentManager
             fragmentManager.beginTransaction().replace(R.id.framelayout, AllDrawerItemsActivity()).commit()*/
        }
    }
}