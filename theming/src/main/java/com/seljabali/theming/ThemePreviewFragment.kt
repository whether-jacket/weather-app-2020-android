package com.seljabali.theming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment

class ThemePreviewFragment : BaseToolbarFragment() {

    companion object {
        val TAG: String = ThemePreviewFragment::class.java.simpleName
        fun newInstance(): ThemePreviewFragment = ThemePreviewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(
        R.layout.fragment_theme_preview, container, false)

    override fun getToolbarTitle(): String = getString(R.string.theme_preview)

}