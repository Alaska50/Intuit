package com.cc.intuit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.cc.intuit.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.backStackEntryCount == 0 && savedInstanceState == null) {
            addAuthFragment()
        }

    }

    private fun addAuthFragment () {
        val fragment = AuthorFragment.newInstance()

        /**
         * NOTE : can use a nav graph if app is going to stick to a single activity
         * with many fragments structure
         */
        supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, fragment).commit()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("key", 0)
    }
}