package com.seljabali.templateapplication.ui.landingpage

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.seljabali.templateapplication.ui.cities.CitiesFragment
import com.seljabali.templateapplication.ui.settings.SettingsFragment
import com.seljabali.templateapplication.ui.weather.WeatherFragment

class LandingViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = LandingPageTabs.values().size

    override fun createFragment(position: Int): Fragment =
        when (position) {
            LandingPageTabs.HOME.ordinal -> WeatherFragment.newInstance()
            LandingPageTabs.CITIES.ordinal -> CitiesFragment.newInstance()
            LandingPageTabs.SETTINGS.ordinal -> SettingsFragment.newInstance()
            else -> throw IllegalStateException("Hit unknown tab position $position")
        }
}