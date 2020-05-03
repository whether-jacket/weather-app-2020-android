package com.seljabali.design.themeselector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import com.seljabali.core.BaseFragment
import com.seljabali.design.R
import com.seljabali.design.landingpage.DesignLandingPageFragment
import kotlinx.android.synthetic.main.fragment_theme_selector.*
import java.lang.ref.WeakReference

class ThemeSelectorFragment : BaseFragment() {

    private lateinit var designLandingFragmentViewer: WeakReference<DesignLandingPageFragment.DesignLandingFragmentViewer>

    companion object {
        val TAG: String = ThemeSelectorFragment::class.java.simpleName
        fun newInstance(): ThemeSelectorFragment = ThemeSelectorFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_theme_selector, container, false)

    override fun getToolbarTitle(): String = getString(R.string.theme_selector)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seamFoamSampleCard.setOnClickListener { setTheme(R.style.Theme_SeaFoam) }
        illiniSampleCard.setOnClickListener { setTheme(R.style.Theme_Illini) }
        tokyoSampleCard.setOnClickListener { setTheme(R.style.Theme_Tokyo) }
    }

    fun setDesignLandingFragmentViewer(viewer: DesignLandingPageFragment.DesignLandingFragmentViewer) {
        designLandingFragmentViewer = WeakReference(viewer)
    }

    private fun setTheme(@StyleRes themeId: Int) {
        designLandingFragmentViewer.get()?.setAppTheme(themeId)
    }
}