package com.seljabali.design.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.design.R
import com.seljabali.design.ThemePreviewFragment
import com.seljabali.design.themeselector.ThemeSelectorFragment
import kotlinx.android.synthetic.main.fragment_design_landing_page.*
import java.lang.ref.WeakReference

class DesignLandingPageFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = DesignLandingPageFragment::class.java.simpleName
        fun newInstance(): DesignLandingPageFragment = DesignLandingPageFragment()
    }

    lateinit var adapter: LandingPageAdapter
    private var designLandingFragmentViewer: WeakReference<DesignLandingFragmentViewer>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(
            R.layout.fragment_design_landing_page, container, false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LandingPageAdapter {
            onPageItemClicked(it)
        }
        adapter.setLandingPageItems(DesignLandingPageItems.values() as Array<LandingItem>)
        landingPageGridView.adapter = adapter
    }

    override fun getToolbarTitle(): String = resources.getString(R.string.design)

    override fun onPause() {
        super.onPause()
        adapter.clearSubscriptions()
    }

    override fun onResume() {
        super.onResume()
        adapter.renewSubscriptions()
    }

    fun setDesignLandingFragmentViewer(viewer: DesignLandingFragmentViewer) {
        designLandingFragmentViewer = WeakReference(viewer)
    }

    private fun onPageItemClicked(landingPageItem: LandingItem) {
        val designLandingFragmentViewer = designLandingFragmentViewer?.get() ?: return
        when (landingPageItem) {
            DesignLandingPageItems.THEME_PREVIEW -> designLandingFragmentViewer.showFragment(ThemePreviewFragment.newInstance(), ThemePreviewFragment.TAG)
            DesignLandingPageItems.THEME_SELECTOR -> {
                val themeSelectorFragment = ThemeSelectorFragment.newInstance()
                themeSelectorFragment.setDesignLandingFragmentViewer(designLandingFragmentViewer)
                designLandingFragmentViewer.showFragment(themeSelectorFragment, ThemeSelectorFragment.TAG)
            }
        }
        return
    }

    interface DesignLandingFragmentViewer {
        fun showFragment(baseFragment: BaseToolbarFragment, tag: String)
        fun setAppTheme(@StyleRes themeId: Int)
    }

}