package com.seljabali.widgets

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.core.utilities.time.DateTimeFormats
import kotlinx.android.synthetic.main.fragment_time_picker.*
import localtime.extensions.print
import java.time.LocalTime

class TimePickerFragment : BaseToolbarFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = TimePickerFragment()

        @JvmStatic
        val TAG: String = TimePickerFragment::class.java.simpleName
        private val TIME_24_FORMAT: String = DateTimeFormats.Time.HH_MM_24.toString()
        private val TIME_AM_PM_FORMAT: String = DateTimeFormats.Time.HH_MM_AM.toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_time_picker, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTime24PickerButton.setOnClickListener { onShowTimeIn24HButtonClicked() }
        showTimeAmPmPickerButton.setOnClickListener { onShowTimeInAmPmButtonClicked() }
    }

    override fun getToolbarTitle(): String = getString(R.string.time_picker)

    private fun onShowTimeInAmPmButtonClicked() {
        val currentTime = LocalTime.now()
        TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                val timePicked = LocalTime.of(hourOfDay, minute).print(TIME_AM_PM_FORMAT)
                Toast.makeText(context, timePicked, Toast.LENGTH_SHORT).show()
            },
            currentTime.hour,
            currentTime.minute,
            false
        ).apply {
            setCancelable(true)
            show()
        }
    }

    private fun onShowTimeIn24HButtonClicked() {
        val currentTime = LocalTime.now()
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                val timePicked = LocalTime.of(hourOfDay, minute).print(TIME_24_FORMAT)
                Toast.makeText(context, timePicked, Toast.LENGTH_SHORT).show()
            },
            currentTime.hour,
            currentTime.minute,
            true
        ).apply {
            setCancelable(true)
            show()
        }
    }
}