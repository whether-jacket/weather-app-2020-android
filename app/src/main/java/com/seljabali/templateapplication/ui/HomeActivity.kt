package com.seljabali.templateapplication.ui

import android.os.Bundle
import android.view.View
import com.seljabali.templateapplication.BaseActivity
import com.seljabali.templateapplication.R
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Tokyo)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        showBackButton(false)
        supportFragmentManager.addOnBackStackChangedListener { backStackChangeListener() }
        showTestPage()
//        showLoginPage()
//        showThemePage()
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }

    private fun backStackChangeListener() {
        val supportActionBar = supportActionBar ?: return
        val isAtHomePage: Boolean = supportFragmentManager.backStackEntryCount < 1
        supportActionBar.setDisplayHomeAsUpEnabled(!isAtHomePage)
        if (isAtHomePage) {
            setToolBarTitle(getString(R.string.app_name))
        }
    }

    fun showToolbarProgressBar(show: Boolean) {
        toolbarProgressBar.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    fun showBackButton(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun showTestPage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, TestFragment.newInstance())
            .commit()
    }

    fun showLoginPage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, LoginFragment.newInstance())
            .commit()
    }

    fun showThemePage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, ThemeFragment.newInstance())
            .commit()
    }
}
