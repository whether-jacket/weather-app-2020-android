package com.seljabali.pages.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment
import com.seljabali.pages.LoginFragment
import com.seljabali.pages.R
import com.seljabali.pages.SettingsFragment
import kotlinx.android.synthetic.main.fragment_pages_landing_page.*
import java.lang.ref.WeakReference

class PagesLandingPageFragment : BaseFragment() {

    companion object {
        val TAG: String = PagesLandingPageFragment::class.java.simpleName
        fun newInstance(): PagesLandingPageFragment = PagesLandingPageFragment()
    }

    lateinit var adapter: LandingPageAdapter
    private var pagesLandingFragmentViewer: WeakReference<PagesLandingFragmentViewer>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(
            R.layout.fragment_pages_landing_page, container, false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LandingPageAdapter {
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

    fun setPagesLandingFragmentViewer(viewer: PagesLandingFragmentViewer) {
        pagesLandingFragmentViewer = WeakReference(viewer)
    }

    private fun onPageItemClicked(landingPageItem: LandingItem) {
        val designLandingFragmentViewer = pagesLandingFragmentViewer?.get() ?: return
        when (landingPageItem) {
            PagesLandingPageItems.LOGIN -> designLandingFragmentViewer.showFragment(LoginFragment.newInstance(), LoginFragment.TAG)
            PagesLandingPageItems.MAPS -> {}
            PagesLandingPageItems.SETTINGS -> designLandingFragmentViewer.showFragment(SettingsFragment.newInstance(), SettingsFragment.TAG)
        }
        return
    }

    interface PagesLandingFragmentViewer {
        fun showFragment(baseFragment: androidx.fragment.app.Fragment, tag: String)
    }

}