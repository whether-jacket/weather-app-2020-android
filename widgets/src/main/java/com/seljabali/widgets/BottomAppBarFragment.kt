package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment

class BottomAppBarFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = BottomAppBarFragment()

        @JvmStatic
        val TAG: String = BottomAppBarFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_bottom_app_bar, container, false)

    override fun getToolbarTitle(): String = getString(R.string.bottom_app_bar)

}
