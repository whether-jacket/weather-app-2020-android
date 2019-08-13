package com.seljabali.widgets

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_chronometer.*

class ChronometerFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ChronometerFragment()
        @JvmStatic
        val TAG: String = ChronometerFragment::class.java.simpleName
    }

    var showingFormat: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_chronometer, container, false)

    override fun getToolbarTitle(): String = getString(R.string.chronometer)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startButton.setOnClickListener { startButtonClicked() }
        stopButton.setOnClickListener { stopButtonClicked() }
        restartButton.setOnClickListener { restartButtonClicked() }
        formatButton.setOnClickListener { formatButtonClicked() }
    }

    private fun startButtonClicked() {
        chronometer.start()
    }

    private fun stopButtonClicked() {
        chronometer.stop()
    }

    private fun restartButtonClicked() {
        chronometer.base = SystemClock.elapsedRealtime()
    }

    private fun formatButtonClicked() {
        showingFormat = !showingFormat
        chronometer.format = if (showingFormat) "Time holding breath: %s" else "%s"
    }
}