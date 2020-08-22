package com.seljabali.templateapplication.ui

import android.os.Bundle
import com.seljabali.core.BaseActivity
import com.seljabali.database.DB_USER_PREFERENCES_BOX
import com.seljabali.templateapplication.R
import com.seljabali.database.models.UserPreferences
import com.seljabali.templateapplication.ui.landingpage.LandingPageFragment
import io.objectbox.Box
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class HomeActivity : BaseActivity() {

    private val userBox: Box<UserPreferences> by inject(named(DB_USER_PREFERENCES_BOX))

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

    private fun getUserPreferences(): UserPreferences {
        if (userBox.all.isEmpty()) {
            userBox.put(UserPreferences())
        }
        return userBox.all[0]
    }
}
