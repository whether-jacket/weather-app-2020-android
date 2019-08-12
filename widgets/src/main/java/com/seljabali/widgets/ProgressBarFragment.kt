package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_progress_bar.*

class ProgressBarFragment: BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): ProgressBarFragment {
            return ProgressBarFragment()
        }

        fun getTag(): String {
            return ProgressBarFragment::class.java.simpleName
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_progress_bar, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnShow.setOnClickListener { onShowClicked() }
        btnHide.setOnClickListener { onHideClicked() }
    }

    override fun onStart() {
        super.onStart()
        activity?.let {
            it.title = getString(R.string.progress_bar)
        }
    }

    private fun onHideClicked() {
        pb_cyclic.visibility = View.INVISIBLE
    }

    private fun onShowClicked() {
        pb_cyclic.visibility = View.VISIBLE
    }
}