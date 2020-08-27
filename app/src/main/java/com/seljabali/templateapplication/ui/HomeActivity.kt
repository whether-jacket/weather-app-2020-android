package com.seljabali.templateapplication.ui

import android.os.Bundle
import com.seljabali.core.activityfragment.nontoolbar.BaseActivity
import com.seljabali.database.DB_USER_PREFERENCES_BOX
import com.seljabali.templateapplication.R
import com.seljabali.database.models.UserPreferencesDb
import com.seljabali.templateapplication.ui.landingpage.LandingPageFragment
import io.objectbox.Box
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class HomeActivity : BaseActivity() {

    private val userBox: Box<UserPreferencesDb> by inject(named(DB_USER_PREFERENCES_BOX))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setTheme(getUserPreferences().themeId)
        setContentView(R.layout.activity_home)
        showLandingPage()
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }

    private fun showLandingPage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_activity_frame_layout, LandingPageFragment.newInstance())
            .commit()
    }

    private fun getUserPreferences(): UserPreferencesDb {
        if (userBox.all.isEmpty()) {
            userBox.put(UserPreferencesDb())
        }
        return userBox.all[0]
    }
}
