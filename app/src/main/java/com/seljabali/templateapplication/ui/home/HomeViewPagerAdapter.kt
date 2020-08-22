package com.seljabali.templateapplication.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.seljabali.templateapplication.ui.cities.CitiesFragment
import com.seljabali.templateapplication.ui.settings.SettingsFragment

class HomeViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = HomePageItems.values().size

    override fun getItem(position: Int): Fragment =
        when (position) {
            HomePageItems.HOME.ordinal -> HomeWeatherFragment.newInstance()
            HomePageItems.CITIES.ordinal -> CitiesFragment.newInstance()
            HomePageItems.SETTINGS.ordinal -> SettingsFragment.newInstance()
            else -> throw IllegalStateException("Hit unknown tab position $position")
        }

}