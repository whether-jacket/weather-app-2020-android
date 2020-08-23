package com.seljabali.templateapplication.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.core.activityfragment.nontoolbar.BaseFragment
import com.seljabali.templateapplication.R

class CitiesFragment : BaseFragment() {

    companion object {
        val TAG: String = CitiesFragment::class.java.simpleName
        fun newInstance() = CitiesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_cities, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}