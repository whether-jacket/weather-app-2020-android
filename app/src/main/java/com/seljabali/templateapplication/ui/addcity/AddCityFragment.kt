package com.seljabali.templateapplication.ui.addcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.seljabali.core.activityfragment.nontoolbar.BaseFragment
import com.seljabali.core.utilities.Keyboard
import com.seljabali.database.DB_LOCATION_BOX
import com.seljabali.database.models.LocationDb
import com.seljabali.templateapplication.R
import io.objectbox.Box
import kotlinx.android.synthetic.main.fragment_add_city.*
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class AddCityFragment : BaseFragment() {

    companion object {
        val TAG: String = AddCityFragment::class.java.simpleName
        fun newInstance() = AddCityFragment()
    }

    private val locationBox: Box<LocationDb> by inject(named(DB_LOCATION_BOX))
    private lateinit var cityAdapter: CitySearchAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_city, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityAdapter = CitySearchAdapter()
        with(cities_results_recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cityAdapter
        }
        city_query_search_view.setOnQueryTextListener(getSearchCloseListener())
        city_query_search_view.requestFocus()
        Keyboard.show(requireContext(), city_query_search_view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Keyboard.hide(this)
    }

    private fun onCitySearchClicked() {
        val queryText = city_query_search_view.query
        if (queryText.isBlank()) return
        city_query_search_view.visibility = View.GONE
        city_query_search_text_view.visibility = View.VISIBLE
        city_query_search_text_view.text = "Searching for $queryText"
        showProgressBar(true)
        Keyboard.hide(this)
    }

    private fun getAllLocationsFromDb(): List<LocationDb> = locationBox.all.sortedBy { it.position }

    private fun onCityAdded(cityName: String) {
        val locationCount = getAllLocationsFromDb().count()
        locationBox.put(
                LocationDb(
                        cityName = cityName,
                        regionName = "",
                        woeId = 0,
                        position = locationCount - 1
                )
        )
        updateAdapterFromDb()
    }

    private fun updateAdapterFromDb() {
//        cityAdapter.dataSet = getAllLocationsFromDb()
    }

    private fun showProgressBar(show: Boolean) {
        city_search_progress_bar.isVisible = show
    }

    private fun getSearchCloseListener() = object : SearchView.OnQueryTextListener {

        override fun onQueryTextChange(s: String): Boolean {
            return true
        }

        override fun onQueryTextSubmit(s: String): Boolean {
            onCitySearchClicked()
            return true
        }
    }

}