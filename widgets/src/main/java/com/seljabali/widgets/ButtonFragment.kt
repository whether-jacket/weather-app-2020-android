package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment

class ButtonFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): ButtonFragment = ButtonFragment()
        fun getTag(): String = ButtonFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_button, container, false)
    }

    override fun onStart() {
        super.onStart()
        activity?.let {
            it.title = getString(R.string.button)
        }
    }
}