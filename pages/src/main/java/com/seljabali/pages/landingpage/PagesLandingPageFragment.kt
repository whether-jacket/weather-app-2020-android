package com.seljabali.pages.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.pages.LoginFragment
import com.seljabali.pages.PagesActivity
import com.seljabali.pages.R
import com.seljabali.pages.SettingsFragment
import kotlinx.android.synthetic.main.fragment_pages_landing_page.*

class PagesLandingPageFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = PagesLandingPageFragment::class.java.simpleName
        fun newInstance() = PagesLandingPageFragment()
    }

    private lateinit var adapter: PagesLandingPageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(
            R.layout.fragment_pages_landing_page, container, false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PagesLandingPageAdapter {
            onPageItemClicked(it)
        }
        adapter.setLandingPageItems(PagesLandingPageItems.values() as Array<LandingItem>)
        landingPageGridView.adapter = adapter
    }

    override fun getToolbarTitle(): String = resources.getString(R.string.pages)

    override fun onPause() {
        super.onPause()
        adapter.clearSubscriptions()
    }

    override fun onResume() {
        super.onResume()
        adapter.renewSubscriptions()
    }

    private fun onPageItemClicked(landingPageItem: LandingItem) {
        val designLandingFragmentViewer = baseActivity as PagesActivity
        when (landingPageItem) {
            PagesLandingPageItems.LOGIN -> designLandingFragmentViewer.showFragment(LoginFragment.newInstance(), LoginFragment.TAG)
            PagesLandingPageItems.MAPS -> {}
            PagesLandingPageItems.SETTINGS -> designLandingFragmentViewer.showFragment(SettingsFragment.newInstance(), SettingsFragment.TAG)
        }
        return
    }
}