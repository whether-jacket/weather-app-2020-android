package com.seljabali.templateapplication.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.seljabali.core.mvi.BaseMviFragment
import com.seljabali.database.DB_LOCATION_BOX
import com.seljabali.database.DB_USER_PREFERENCES_BOX
import com.seljabali.database.DB_WEATHER_FOR_LOCATION_BOX
import com.seljabali.database.models.LocationDb
import com.seljabali.database.models.UserPreferencesDb
import com.seljabali.database.models.WeatherForLocationDb
import com.seljabali.templateapplication.R
import com.seljabali.templateapplication.ui.weather.cityregionadapter.CityRegion
import com.seljabali.templateapplication.ui.weather.cityregionadapter.CityRegionAdapter
import io.objectbox.Box
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_weather_landing_page.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class WeatherFragment : BaseMviFragment<WeatherViewEvent, WeatherViewState, WeatherSideEffect>(
    module = weatherModule
), WeatherViewApi {

    companion object {
        val TAG: String = WeatherFragment::class.java.simpleName
        fun newInstance() = WeatherFragment()
    }

    private val locationBox: Box<LocationDb> by inject(named(DB_LOCATION_BOX))
    private val weatherForLocationBox: Box<WeatherForLocationDb> by inject(named(DB_WEATHER_FOR_LOCATION_BOX))
    private val userPreferencesBox: Box<UserPreferencesDb> by inject(named(DB_USER_PREFERENCES_BOX))

    private val loadSfWeatherEventPublisher = BehaviorSubject.create<WeatherViewEvent.LoadWeatherPageEvent>()
    private val loadCityPositionEventPublisher = BehaviorSubject.create<WeatherViewEvent.LoadCityPositionEvent>()
    private lateinit var viewStateBinder: WeatherViewStateBinder
    private lateinit var cityRegionAdapter: CityRegionAdapter
    override val viewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDatabase()
        viewModel.processViewEvents(
            Observable.merge(
                Observable.just(loadSfWeatherEventPublisher, loadCityPositionEventPublisher)
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_weather_landing_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewStateBinder = WeatherViewStateBinder(this)
        loadSfWeatherEventPublisher.onNext(WeatherViewEvent.LoadWeatherPageEvent)
        citiesViewPagerSetup()
    }

    private fun citiesViewPagerSetup() {
        cityRegionAdapter = CityRegionAdapter()
        cities_view_pager.apply {
            isUserInputEnabled = true
            adapter = cityRegionAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    loadCityPositionEventPublisher.onNext(WeatherViewEvent.LoadCityPositionEvent(position))
                }
            })
        }
    }

    private fun setupDatabase() {
        clearDatabase()
        val sf = LocationDb(cityName = "San Francisco", regionName = "CA", woeId = 2487956, position = 0)
        val sfWeather = WeatherForLocationDb(
                id = 0,
                theTemp = 35f,
                pressure = 1023f,
                humidity = 52f,
                windSpeed = 3.13f,
                dateTimeOfFetch = "2020-11-29"
        ).apply { location.target = sf }
        weatherForLocationBox.put(sfWeather)

        val phx = LocationDb(cityName = "Phoenix", regionName = "AZ", woeId = 2471390, position = 1)
        val phxWeather = WeatherForLocationDb(
                id = 0,
                theTemp = 65f,
                pressure = 634f,
                humidity = 30f,
                windSpeed = 14f,
                dateTimeOfFetch = "2020-11-29",
        ).apply { location.target = phx }
        weatherForLocationBox.put(phxWeather)

        val ny = LocationDb(cityName = "New York", regionName = "NY", woeId = 2459115, position = 2)
        val nyWeather = WeatherForLocationDb(
                id = 0,
                theTemp = 15f,
                pressure = 23f,
                humidity = 113f,
                windSpeed = 26f,
                dateTimeOfFetch = "2020-11-29",
        ).apply { location.target = ny  }
        weatherForLocationBox.put(nyWeather)
    }

    private fun clearDatabase() {
        locationBox.removeAll()
        weatherForLocationBox.removeAll()
    }

    /**
     * MVI Events
     */
    override fun onViewStateEvent(viewState: WeatherViewState) {
        viewStateBinder.setViewState(viewState)
    }

    override fun onSideEffectEvent(sideEffect: WeatherSideEffect) {
        when (sideEffect) {
            is WeatherSideEffect.ShowToast -> Toast.makeText(
                context,
                sideEffect.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     *  WeatherView Api
     */
    override fun setCityRegionsVisibility(toShow: Boolean) {
        cities_view_pager.visibility = if (toShow) View.VISIBLE else View.INVISIBLE
    }

    override fun setCityRegions(cityRegions: List<CityRegion>) {
        cityRegionAdapter.setCityRegions(cityRegions)
    }

    override fun setTemperature(text: String) {
        temperature_text_view.text = text
    }

    override fun setWeatherImageVisibility(toShow: Boolean) {
        weather_image_view.visibility = if (toShow) View.VISIBLE else View.INVISIBLE
    }

    override fun setProgressBarVisibility(toShow: Boolean) {
        progressBar.visibility = if (toShow) View.VISIBLE else View.INVISIBLE
    }

    override fun setPressureTitleVisibility(toShow: Boolean) {
        pressure_label_text_view.visibility = if (toShow) View.VISIBLE else View.INVISIBLE
    }

    override fun setPressure(text: String) {
        pressure_text_view.text = text
    }

    override fun setHumidityTitleVisibility(toShow: Boolean) {
        humidity_label_text_view.visibility = if (toShow) View.VISIBLE else View.INVISIBLE
    }

    override fun setHumidity(text: String) {
        humidity_text_view.text = text
    }

    override fun setWindSpeedTitleVisibility(toShow: Boolean) {
        wind_speed_label_text_view.visibility = if (toShow) View.VISIBLE else View.INVISIBLE
    }

    override fun setWindSpeed(text: String) {
        wind_speed_text_view.text = text
    }

    override fun setDateTime(text: String) {
        date_time_text_view.text = text
    }
}
