package com.seljabali.templateapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.templateapplication.BaseFragment
import com.seljabali.templateapplication.R
import com.seljabali.templateapplication.utilities.getTextValue
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : BaseFragment() {

    companion object {
        val TAG: String = TestFragment::class.java.simpleName
        fun newInstance(): TestFragment = TestFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(
        R.layout.fragment_test, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCalculate.setOnClickListener { onCalculateClick() }
    }

    private fun onCalculateClick() {
        val input = etInputText.getTextValue()
        tvOutput.text = getOutput(input)
    }

    private fun getOutput(input: String) : String {
        return input
    }
}