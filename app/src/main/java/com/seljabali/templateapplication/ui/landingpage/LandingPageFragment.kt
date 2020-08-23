package com.seljabali.templateapplication.ui.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.nontoolbar.BaseFragment
import com.seljabali.templateapplication.R
import kotlinx.android.synthetic.main.fragment_landing_page.*
import java.lang.IllegalStateException

class LandingPageFragment : BaseFragment() {

    companion object {
        val TAG: String = LandingPageFragment::class.java.simpleName
        fun newInstance() = LandingPageFragment()
    }

    private lateinit var viewPagerAdapter: LandingViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_landing_page, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        val homeActivity = activity ?: return
        viewPagerAdapter = LandingViewPagerAdapter(homeActivity.supportFragmentManager)
        landing_page_view_pager.adapter = viewPagerAdapter
        landing_bottom_navigation_view.setOnNavigationItemSelectedListener { item: MenuItem ->
            val landingPageTabs: LandingPageTabs = LandingPageTabs.getMenuIdOf(item.itemId)
                ?: throw IllegalStateException("hit unknown menu item")
            landing_page_view_pager.currentItem = landingPageTabs.ordinal
            return@setOnNavigationItemSelectedListener true
        }
    }
}