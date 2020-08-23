package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import kotlinx.android.synthetic.main.fragment_progress_bar.*

class ProgressBarFragment: BaseToolbarFragment() {

    companion object {
        @JvmStatic
        val TAG: String = ProgressBarFragment::class.java.simpleName
        @JvmStatic
        fun newInstance(): ProgressBarFragment = ProgressBarFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_progress_bar, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnShow.setOnClickListener { onShowClicked() }
        btnHide.setOnClickListener { onHideClicked() }
    }

    override fun getToolbarTitle(): String = getString(R.string.progress_bar)

    private fun onHideClicked() {
        pb_cyclic.visibility = View.INVISIBLE
    }

    private fun onShowClicked() {
        pb_cyclic.visibility = View.VISIBLE
    }
}
