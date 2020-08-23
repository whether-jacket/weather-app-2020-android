package com.seljabali.theming.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.theming.ThemingActivity
import com.seljabali.theming.R
import com.seljabali.theming.ThemePreviewFragment
import com.seljabali.theming.themeselector.ThemeSelectorFragment
import kotlinx.android.synthetic.main.fragment_design_landing_page.*

class ThemingLandingPageFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = ThemingLandingPageFragment::class.java.simpleName
        fun newInstance() = ThemingLandingPageFragment()
    }

    lateinit var adapter: ThemingLandingPageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(
            R.layout.fragment_design_landing_page, container, false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ThemingLandingPageAdapter {
            onPageItemClicked(it)
        }
        adapter.setLandingPageItems(ThemingLandingPageItems.values() as Array<LandingItem>)
        landingPageGridView.adapter = adapter
    }

    override fun getToolbarTitle(): String = resources.getString(R.string.theming)

    override fun onPause() {
        super.onPause()
        adapter.clearSubscriptions()
    }

    override fun onResume() {
        super.onResume()
        adapter.renewSubscriptions()
    }

    private fun onPageItemClicked(landingPageItem: LandingItem) {
        val themingActivity = baseActivity as ThemingActivity ?: return
        when (landingPageItem) {
            ThemingLandingPageItems.THEME_PREVIEW -> themingActivity.showFragment(ThemePreviewFragment.newInstance(), ThemePreviewFragment.TAG)
            ThemingLandingPageItems.THEME_SELECTOR -> themingActivity.showFragment(ThemeSelectorFragment.newInstance(), ThemeSelectorFragment.TAG)
        }
        return
    }
}