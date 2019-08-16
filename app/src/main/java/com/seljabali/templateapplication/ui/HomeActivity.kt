package com.seljabali.templateapplication.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.StyleRes
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.seljabali.core.BaseActivity
import com.seljabali.core.BaseFragment
import com.seljabali.design.landingpage.DesignLandingPageFragment
import com.seljabali.templateapplication.ObjectBox
import com.seljabali.templateapplication.R
import com.seljabali.templateapplication.models.UserPreferences
import com.seljabali.templateapplication.ui.landingpage.LandingPageFragment
import com.seljabali.widgets.landing.WidgetsLandingFragment
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : BaseActivity(), WidgetsLandingFragment.WidgetsLandingFragmentViewer,
    DesignLandingPageFragment.DesignLandingFragmentViewer {

    private val userBox = ObjectBox.get().boxFor(UserPreferences::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getUserPreferences().themeId)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        showBackButton(false)
        supportFragmentManager.addOnBackStackChangedListener { backStackChangeListener() }
        showLandingPage()
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

    fun showToolbarProgressBar(show: Boolean) {
        toolbarProgressBar.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    fun showBackButton(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showLandingPage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, LandingPageFragment.newInstance())
            .commit()
    }

    private fun backStackChangeListener() {
        val supportActionBar = supportActionBar ?: return
        val isAtHomePage: Boolean = supportFragmentManager.backStackEntryCount < 1
        supportActionBar.setDisplayHomeAsUpEnabled(!isAtHomePage)
        if (isAtHomePage) {
            setToolBarTitle(getString(R.string.app_name))
        }
    }

    private fun getUserPreferences(): UserPreferences {
        if (userBox.all.isEmpty()) {
            userBox.put(UserPreferences())
        }
        return userBox.all[0]
    }
}
