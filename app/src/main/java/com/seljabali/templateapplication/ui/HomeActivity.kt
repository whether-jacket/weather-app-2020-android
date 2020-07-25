package com.seljabali.templateapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.StyleRes
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.google.android.material.appbar.AppBarLayout
import com.seljabali.core.BaseActivity
import com.seljabali.core.BaseFragment
import com.seljabali.database.DB_USER_PREFERENCES_BOX
import com.seljabali.design.landingpage.DesignLandingPageFragment
import com.seljabali.pages.landingpage.PagesLandingPageFragment
import com.seljabali.templateapplication.R
import com.seljabali.database.models.UserPreferences
import com.seljabali.templateapplication.ui.landingpage.LandingPageFragment
import com.seljabali.templateapplication.ui.weather.WeatherFragment
import com.seljabali.widgets.landing.WidgetsLandingFragment
import io.objectbox.Box
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class HomeActivity : BaseActivity(), WidgetsLandingFragment.WidgetsLandingFragmentViewer,
    DesignLandingPageFragment.DesignLandingFragmentViewer, PagesLandingPageFragment.PagesLandingFragmentViewer {

    private val userBox: Box<UserPreferences> by inject(named(DB_USER_PREFERENCES_BOX))
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var toolbarProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getUserPreferences().themeId)
        setContentView(R.layout.activity_home)
        setupToolbar()
//        showLandingPage()
        showMainScreen()
    }

    private fun showMainScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, WeatherFragment.newInstance())
            .commit()
    }

    override fun onBackStackChanged() {
        super.onBackStackChanged()
        val supportActionBar = supportActionBar ?: return
        val isAtHomePage: Boolean = supportFragmentManager.backStackEntryCount < 1
        supportActionBar.setDisplayHomeAsUpEnabled(!isAtHomePage)
        if (isAtHomePage) {
            setToolBarTitle(getString(R.string.app_name))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }

    override fun showFragment(baseFragment: BaseFragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .add(R.id.frameLayout, baseFragment)
            .addToBackStack(tag)
            .commit()
    }

    override fun showFragment(baseFragment: androidx.fragment.app.Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .add(R.id.frameLayout, baseFragment)
            .addToBackStack(tag)
            .commit()
    }

    override fun setAppTheme(@StyleRes themeId: Int) {
        userBox.put(getUserPreferences().apply {
            this.themeId = themeId
        })
        setTheme(themeId)
        supportFragmentManager.popBackStack(0, POP_BACK_STACK_INCLUSIVE)
        recreate()
    }

    fun showWidgetCatalogue() {
        val widgetsLandingFragment = WidgetsLandingFragment.newInstance()
        widgetsLandingFragment.setWidgetsLandingFragmentViewer(this)
        showFragment(widgetsLandingFragment, WidgetsLandingFragment.TAG)
    }

    fun showDesignFragment() {
        val designLandingPageFragment = DesignLandingPageFragment.newInstance()
        designLandingPageFragment.setDesignLandingFragmentViewer(this)
        showFragment(designLandingPageFragment, DesignLandingPageFragment.TAG)
    }

    fun showPagesFragment() {
        val pagesLandingPageFragment = PagesLandingPageFragment.newInstance()
        pagesLandingPageFragment.setPagesLandingFragmentViewer(this)
        showFragment(pagesLandingPageFragment, PagesLandingPageFragment.TAG)
    }

    fun showToolbarProgressBar(show: Boolean) {
        toolbarProgressBar.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setupToolbar() {
        val appBar = findViewById<AppBarLayout>(R.id.appToolbar)
        toolbar = appBar.findViewById(R.id.toolbar)
        toolbarProgressBar = appBar.findViewById(R.id.toolbarProgressBar)
        setSupportActionBar(toolbar)
        showBackButton(false)
    }

    private fun showLandingPage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, LandingPageFragment.newInstance())
            .commit()
    }

    private fun getUserPreferences(): UserPreferences {
        if (userBox.all.isEmpty()) {
            userBox.put(UserPreferences())
        }
        return userBox.all[0]
    }
}
