package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment

class ButtonFragment : BaseFragment() {

    companion object {
        @JvmStatic
        val TAG: String = ButtonFragment::class.java.simpleName
        @JvmStatic
        fun newInstance(): ButtonFragment = ButtonFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_button, container, false)

    override fun onStart() {
        super.onStart()
        baseActivity.title = getString(R.string.button)
    }
}