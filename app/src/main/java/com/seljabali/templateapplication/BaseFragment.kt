package com.seljabali.templateapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.v("${getDisplayTag()} View Created")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.v("${getDisplayTag()} View Destroyed")
    }

    private fun getDisplayTag(): String = javaClass.simpleName
}