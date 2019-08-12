package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment

class CheckBoxFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = CheckBoxFragment()
        @JvmStatic
        val TAG: String = CheckBoxFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_checkbox, container, false)

    override fun onStart() {
        super.onStart()
        baseActivity.title = getString(R.string.checkbox)
    }
}