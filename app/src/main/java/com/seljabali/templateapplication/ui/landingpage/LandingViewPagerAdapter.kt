package com.seljabali.templateapplication.ui.landingpage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.seljabali.templateapplication.ui.cities.CitiesFragment
import com.seljabali.templateapplication.ui.settings.SettingsFragment
import com.seljabali.templateapplication.ui.weather.WeatherFragment

class LandingViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = LandingPageTabs.values().size

    override fun getItem(position: Int): Fragment =
        when (position) {
            LandingPageTabs.HOME.ordinal -> WeatherFragment.newInstance()
            LandingPageTabs.CITIES.ordinal -> CitiesFragment.newInstance()
            LandingPageTabs.SETTINGS.ordinal -> SettingsFragment.newInstance()
            else -> throw IllegalStateException("Hit unknown tab position $position")
        }

}