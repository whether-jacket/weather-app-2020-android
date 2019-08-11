package com.seljabali.templateapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.BaseFragment
import com.seljabali.templateapplication.R
import com.seljabali.core.utilities.getTextValue
import kotlinx.android.synthetic.main.fragment_test.*
import java.math.BigInteger

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
        val output = getOutput(input)
        tvOutput.text = output
    }

    private fun getOutput(input: String) : String {
        var output = ""
        return output
    }

    fun fib(nth: Long): BigInteger {
        var nth = nth - 1
        var count: Long = 0
        var first = BigInteger.ZERO
        var second = BigInteger.ONE

        lateinit var third: BigInteger
        while (count < nth) {
            third = BigInteger(first.add(second).toString())
            first = BigInteger(second.toString())
            second = BigInteger(third.toString())
            count++
        }
        return third
    }

    private fun getPlainText(): String = """
        {
            "name": "Sami"
        }
    """.trimIndent()
}