package com.seljabali.widgets

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seljabali.core.activityfragment.toolbar.BaseToolbarFragment
import com.seljabali.core.utilities.time.Formats
import com.seljabali.core.utilities.time.NOW
import com.seljabali.core.utilities.time.ZonedDateTimeUtil
import com.seljabali.core.utilities.time.print
import com.seljabali.core.utilities.time.getMonthBaseZero
import kotlinx.android.synthetic.main.fragment_date_picker.*

class DatePickerFragment : BaseToolbarFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = DatePickerFragment()

        @JvmStatic
        val TAG: String = DatePickerFragment::class.java.simpleName
        private val DATE_FORMAT: String = Formats.MonthDayYear.MM_DD_YYYY_SLASH.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_date_picker, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDatePickerButton.setOnClickListener { onShowDatePickerButtonClicked() }
    }

    override fun getToolbarTitle(): String = getString(R.string.date_picker)

    private fun onShowDatePickerButtonClicked() {
        val now = NOW()
        DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val datePicked = ZonedDateTimeUtil.new(
                    year = year,
                    month = month,
                    day = dayOfMonth,
                    hour = 0,
                    minute = 0,
                    second = 0
                )
                Toast.makeText(context, datePicked.print(DATE_FORMAT), Toast.LENGTH_SHORT).show()
            },
            now.year,
            now.getMonthBaseZero(),
            now.dayOfMonth
        ).show()
    }
}
