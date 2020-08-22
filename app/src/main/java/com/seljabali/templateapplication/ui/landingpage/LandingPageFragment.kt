package com.seljabali.templateapplication.ui.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment
import com.seljabali.templateapplication.R
import com.seljabali.templateapplication.ui.home.HomePageItems
import com.seljabali.templateapplication.ui.home.HomeViewPagerAdapter
import com.seljabali.templateapplication.ui.home.HomeWeatherFragment
import com.seljabali.templateapplication.ui.weather.WeatherFragment
import kotlinx.android.synthetic.main.fragment_landing_page.*
import java.lang.IllegalStateException

class LandingPageFragment : BaseFragment() {

    companion object {
        val TAG: String = LandingPageFragment::class.java.simpleName
        fun newInstance() = LandingPageFragment()
    }

    private lateinit var viewPagerAdapter: HomeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_landing_page, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMainScreen()
        setupView()
    }

    private fun setupView() {
        val homeActivity = activity ?: return
        viewPagerAdapter = HomeViewPagerAdapter(homeActivity.supportFragmentManager)
        landing_page_view_pager.adapter = viewPagerAdapter
        home_bottom_navigation_view.setOnNavigationItemSelectedListener { item: MenuItem ->
            val homePageItems: HomePageItems = HomePageItems.getMenuIdOf(item.itemId)
                ?: throw IllegalStateException("hit unknown menu item")
            landing_page_view_pager.currentItem = homePageItems.ordinal
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun showMainScreen() {
        val homeActivity = activity ?: return
        homeActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.landing_page_frame_layout, HomeWeatherFragment.newInstance())
            .commit()
    }
}