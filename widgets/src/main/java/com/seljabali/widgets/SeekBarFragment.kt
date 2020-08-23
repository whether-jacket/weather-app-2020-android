package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment

class SeekBarFragment : BaseToolbarFragment() {

    companion object {
        @JvmStatic
        val TAG: String = SeekBarFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): SeekBarFragment = SeekBarFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_abs_seek_bar, container, false)

    override fun getToolbarTitle(): String = getString(R.string.seek_bar)
}