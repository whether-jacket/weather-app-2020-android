package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment

class ButtonFragment : BaseToolbarFragment() {

    companion object {
        @JvmStatic
        val TAG: String = ButtonFragment::class.java.simpleName
        @JvmStatic
        fun newInstance(): ButtonFragment = ButtonFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_button, container, false)

    override fun getToolbarTitle(): String = getString(R.string.button)
}