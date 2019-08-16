package com.seljabali.templateapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment
import com.seljabali.templateapplication.R

class ThemeFragment : BaseFragment() {

    companion object {
        val TAG: String = ThemeFragment::class.java.simpleName
        fun newInstance(): ThemeFragment = ThemeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(
        R.layout.fragment_theme, container, false)

    override fun getToolbarTitle(): String = getString(R.string.theme)
}