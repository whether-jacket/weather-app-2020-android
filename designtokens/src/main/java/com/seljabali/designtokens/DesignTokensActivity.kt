package com.seljabali.designtokens

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.seljabali.core.activityfragment.toolbar.BaseToolbarActivity
import com.seljabali.designtokens.landingpage.DesignTokensLandingPageFragment

class DesignTokensActivity : BaseToolbarActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_design_tokens)
        setupToolbar()
        showLandingPage()
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }

    fun showFragment(baseFragment: androidx.fragment.app.Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .add(R.id.design_tokens_activity_frame_layout, baseFragment)
            .addToBackStack(tag)
            .commit()
    }

    private fun setupToolbar() {
        val appBar = findViewById<AppBarLayout>(R.id.appToolbar)
        toolbar = appBar.findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        showBackButton(false)
        setToolbarTitle(getString(R.string.design_tokens))
    }

    private fun showLandingPage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.design_tokens_activity_frame_layout, DesignTokensLandingPageFragment.newInstance())
            .commit()
    }
}