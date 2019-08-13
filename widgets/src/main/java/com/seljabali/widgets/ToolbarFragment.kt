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
        val TAG: String = ToolbarFragment::class.java.simpleName
        @JvmStatic
        fun newInstance(): ToolbarFragment = ToolbarFragment()
        private val firstColor: Int = R.color.brown
        private val secondColor: Int = R.color.whiteSmoke
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

    override fun getToolbarTitle(): String = getString(R.string.toolbar)

    private fun onChangeColorClicked() {
        colorSelected = if (colorSelected == null || colorSelected == secondColor) firstColor else secondColor
        setToolbarColor(colorSelected!!)
    }

    @SuppressLint("RestrictedApi")
    private fun onChangeElevationClicked() {
        elevationSelected = if (elevationSelected == null || elevationSelected == 0f) resources.getDimension(R.dimen.m_elevation) else 0f
        val actionBar = baseActivity.supportActionBar ?: return
        actionBar.apply {
            elevation = elevationSelected!!
            invalidateOptionsMenu()
        }
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