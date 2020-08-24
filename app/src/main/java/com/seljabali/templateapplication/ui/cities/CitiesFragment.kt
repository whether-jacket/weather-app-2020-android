package com.seljabali.templateapplication.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.seljabali.core.activityfragment.nontoolbar.BaseFragment
import com.seljabali.templateapplication.R
import kotlinx.android.synthetic.main.fragment_cities.*
import setTheme

class CitiesFragment : BaseFragment(), AddCityDialogListener {

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
        add_city_button.setOnClickListener { showAddCityDialog() }
    }

    private fun showAddCityDialog() {
        val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        val prevDialog = parentFragmentManager.findFragmentByTag(AddCityDialog.TAG)
        prevDialog?.let { fragmentTransaction.remove(it) }
        fragmentTransaction.addToBackStack(null)

        val addCityDialog = AddCityDialog.newInstance()
        addCityDialog.addCityDialogListener = this
        addCityDialog.show(fragmentTransaction, AddCityDialog.TAG)
    }

    override fun onCityAdded(cityName: String) {
        Toast.makeText(requireContext(), "$cityName was added", Toast.LENGTH_SHORT).setTheme().show()
    }
}