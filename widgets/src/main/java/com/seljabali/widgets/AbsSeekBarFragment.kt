package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment

class AbsSeekBarFragment : BaseFragment() {

    companion object {
        @JvmStatic
        val TAG: String = AbsSeekBarFragment::class.java.simpleName
        @JvmStatic
        fun newInstance(): AbsSeekBarFragment = AbsSeekBarFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_abs_seek_bar, container, false)

    override fun onStart() {
        super.onStart()
        baseActivity.title = getString(R.string.seek_bar)
    }
}