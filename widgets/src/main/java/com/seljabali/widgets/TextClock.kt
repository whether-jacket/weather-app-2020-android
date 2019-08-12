package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_text_clock.*

class TextClock  : BaseFragment() {

    companion object {
        @JvmStatic
        val TAG: String = TextClock::class.java.simpleName

        @JvmStatic
        fun newInstance(): TextClock = TextClock()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_text_clock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn24Hrs.setOnClickListener{ on24HrsClicked() }
        btnAmPm.setOnClickListener{ onAmPmClicked() }
        onAmPmClicked()
    }

    private fun onAmPmClicked() {
        textClockAmPm.visibility = View.VISIBLE
        textClock24.visibility = View.GONE
    }

    private fun on24HrsClicked() {
        textClock24.visibility = View.VISIBLE
        textClockAmPm.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        activity?.let {
            it.title = getString(R.string.text_clock)
        }
    }
}