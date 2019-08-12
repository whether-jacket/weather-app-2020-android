package com.seljabali.widgets

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.seljabali.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_toolbar.*

class ToolbarFragment: BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): ToolbarFragment = ToolbarFragment()
        fun getTag(): String = ToolbarFragment::class.java.simpleName
    }

    private var colorSelected :Int? = null
    private var elevationSelected :Float? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_toolbar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnShow.setOnClickListener { onShowToolbarClicked() }
        btnHide.setOnClickListener { onHideToolbarClicked() }
        btnChangeElevation.setOnClickListener { onChangeElevationClicked() }
        btnChangeColor.setOnClickListener { onChangeColorClicked() }
    }

    override fun onStart() {
        super.onStart()
        activity?.let {
            it.title = getString(R.string.toolbar)
        }
    }

    private fun onChangeColorClicked() {
        colorSelected = if (colorSelected == null || colorSelected == android.R.attr.colorPrimary) {
            android.R.attr.colorPrimaryDark
        } else {
            android.R.attr.colorPrimary
        }
        setToolbarColor(colorSelected!!)
    }

    @SuppressLint("RestrictedApi")
    private fun onChangeElevationClicked() {
        elevationSelected = if (elevationSelected == null || elevationSelected == 0f) {
            resources.getDimension(R.dimen.m_elevation)
        } else {
            0f
        }
        val actionBar = baseActivity.supportActionBar ?: return
        actionBar.elevation = elevationSelected!!
        actionBar.invalidateOptionsMenu()
    }

    private fun onHideToolbarClicked() {
        showToolbar(false)
    }

    private fun onShowToolbarClicked() {
        showToolbar(true)
    }

    private fun showToolbar(visible: Boolean) {
        val actionBar = baseActivity.supportActionBar ?: return
        if (visible) {
            actionBar.show()
        } else {
            actionBar.hide()
        }
    }

    private fun setToolbarColor(@ColorRes color: Int) {
        val actionBar = baseActivity.supportActionBar ?: return
        actionBar.setBackgroundDrawable(resources.getDrawable(color))
    }

}