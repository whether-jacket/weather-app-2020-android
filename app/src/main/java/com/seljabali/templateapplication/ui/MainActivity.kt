package com.seljabali.templateapplication.ui

import android.os.Bundle
import android.view.View
import com.seljabali.templateapplication.BaseActivity
import com.seljabali.templateapplication.R
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        showBackButton(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }

    fun showToolbarProgressBar(show: Boolean) {
        toolbarProgressBar.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    fun showBackButton(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }
}
