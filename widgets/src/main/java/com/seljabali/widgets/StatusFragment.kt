package com.seljabali.widgets

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import kotlinx.android.synthetic.main.fragment_status.*

class StatusFragment : BaseToolbarFragment() {

    companion object {
        @JvmStatic
        val TAG: String = StatusFragment::class.java.simpleName
        @JvmStatic
        fun newInstance(): StatusFragment = StatusFragment()
        private val firstColor: Int = R.color.brown
        private val secondColor: Int = R.color.whiteSmoke

    }

    var color: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_status, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnHide.setOnClickListener { onHideClicked() }
        btnShow.setOnClickListener { onShowClicked() }
        btnChangeColor.setOnClickListener { onChangeColorClicked() }
    }

    override fun getToolbarTitle(): String = getString(R.string.status_bar)

    private fun onHideClicked() {
        activity?.window?.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
    }

    private fun onShowClicked() {
        activity?.window?.clearFlags(FLAG_FULLSCREEN)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onChangeColorClicked() {
        color = if (color == null || color == secondColor) firstColor else secondColor
        activity?.window?.statusBarColor = resources.getColor(color!!)
    }
}
