package com.seljabali.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seljabali.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_radio_button.*

class RadioButtonFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = RadioButtonFragment()
        @JvmStatic
        val TAG: String = RadioButtonFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_radio_button, container, false)

    override fun getToolbarTitle(): String = getString(R.string.radio_button)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        someRadioGroup.setOnCheckedChangeListener { radioGroup, radioButtonId ->
            onRadioGroupChange(radioButtonId)
        }
    }

    private fun onRadioGroupChange(radioButtonId: Int) {
        when (radioButtonId) {
            R.id.fooRadioButton -> Toast.makeText(context, R.string.foo, Toast.LENGTH_SHORT).show()
            R.id.barRadioButton -> Toast.makeText(context, R.string.bar, Toast.LENGTH_SHORT).show()
        }
    }
}
