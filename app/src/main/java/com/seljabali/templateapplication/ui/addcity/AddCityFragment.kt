package com.seljabali.templateapplication.ui.addcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.seljabali.core.activityfragment.nontoolbar.BaseFragment
import com.seljabali.core.modules.RxProvider
import com.seljabali.core.utilities.Keyboard
import com.seljabali.core.utilities.Res
import com.seljabali.database.DB_LOCATION_BOX
import com.seljabali.database.models.LocationDb
import com.seljabali.network.MetaWeatherService
import com.seljabali.templateapplication.R
import io.objectbox.Box
import kotlinx.android.synthetic.main.fragment_add_city.*
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import setTheme

class AddCityFragment : BaseFragment() {

    companion object {
        val TAG: String = AddCityFragment::class.java.simpleName
        fun newInstance() = AddCityFragment()
    }

    private val locationBox: Box<LocationDb> by inject(named(DB_LOCATION_BOX))
    private val weatherApi: MetaWeatherService by inject()
    private val rxProvider: RxProvider by inject()
    private lateinit var cityAdapter: CitySearchAdapter
    private val locationsOnDb: List<LocationDb> = locationBox.all

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_city, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        cityAdapter = CitySearchAdapter(onCityClickedListener)
        with(cities_results_recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
            adapter = cityAdapter
        }
        showKeyboard(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showKeyboard(false)
    }

    private fun setupToolbar() {
        val backButtonDrawableId = Res.getResIdFromAttribute(requireContext(), android.R.attr.homeAsUpIndicator)
        with(city_search_toolbar) {
            navigationIcon = Res.getDrawable(requireContext(), backButtonDrawableId)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
        with(city_query_search_view) {
            setOnQueryTextListener(getSearchQueryTextListener())
            requestFocus()
        }
    }

    private fun onCitySearchQueried() {
        val queryText = city_query_search_view.query.toString()
        if (queryText.isBlank()) return
        showProgressBar(true)
        showKeyboard(false)
        subscribe(weatherApi.getLocationsForCitySearch(cityName = queryText)
                .subscribeOn(rxProvider.ioScheduler())
                .observeOn(rxProvider.uiScheduler())
                .map { locations ->
                    locations.map { location ->
                        CityResult(
                                cityName = location.cityTitle,
                                regionName = location.location_type,
                                whereOnEarthId = location.whereOnEarthId
                        )
                    }
                }
                .doFinally {
                    showProgressBar(false)
                }
                .subscribe({ result ->
                    cityAdapter.setCityResults(results = result)
                }, { error ->
                    Logger.e(error.message ?: error.toString())
                }))
    }

    private fun showProgressBar(show: Boolean) {
        city_search_progress_bar.isVisible = show
    }

    private fun showKeyboard(show: Boolean) {
        if (show) {
            Keyboard.show(requireContext(), city_query_search_view)
            return
        }
        Keyboard.hide(requireContext(), city_query_search_view)
    }

    private val onCityClickedListener: (CityResult) -> Unit = { cityTapped ->
        if (locationsOnDb.firstOrNull { it.woeId == cityTapped.whereOnEarthId } != null) {
            Toast.makeText(
                    requireContext(),
                    getString(R.string.city_already_exists, cityTapped.cityName),
                    Toast.LENGTH_LONG
            ).setTheme().show()
        } else {
            val locationCount = locationsOnDb.count()
            locationBox.put(
                    LocationDb(
                            cityName = cityTapped.cityName,
                            regionName = "",
                            woeId = cityTapped.whereOnEarthId,
                            position = locationCount - 1
                    )
            )
            Toast.makeText(
                    requireContext(),
                    getString(R.string.city_added_to_favorites, cityTapped.cityName),
                    Toast.LENGTH_LONG
            ).setTheme().show()
            activity?.onBackPressed()
        }
        Unit
    }

    private fun getSearchQueryTextListener() = object : SearchView.OnQueryTextListener {

        override fun onQueryTextChange(s: String): Boolean = true

        override fun onQueryTextSubmit(s: String): Boolean {
            onCitySearchQueried()
            return true
        }
    }

}