package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment

class AnalogClock : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): AnalogClock = AnalogClock()
        fun getTag(): String = AnalogClock::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_analog_clock, container, false)
    }

    override fun onStart() {
        super.onStart()
        activity?.let {
            it.title = getString(R.string.analog_clock)
        }
    }
}