package com.seljabali.theming.themeselector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.theming.R
import kotlinx.android.synthetic.main.fragment_theme_selector.*

class ThemeSelectorFragment : BaseToolbarFragment() {

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

    private fun setTheme(@StyleRes themeId: Int) {
        baseActivity.setTheme(themeId)
        baseActivity.supportFragmentManager.popBackStack(0, POP_BACK_STACK_INCLUSIVE)
        baseActivity.recreate()
    }
}