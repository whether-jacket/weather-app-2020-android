package com.seljabali.templateapplication.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seljabali.core.mvi.BaseMviFragment
import com.seljabali.core.utilities.setUnderlined
import com.seljabali.templateapplication.R
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_weather_landing_page.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : BaseMviFragment<WeatherViewEvent, WeatherViewState, WeatherSideEffect>(
    module = weatherModule
), WeatherViewApi {

    companion object {
        val TAG: String = WeatherFragment::class.java.simpleName
        fun newInstance() = WeatherFragment()
    }

    private val loadSfWeatherEventPublisher =
        BehaviorSubject.create<WeatherViewEvent.LoadSfWeatherEvent>()
    private val viewStateBinder = WeatherViewStateBinder()

    override val viewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.processViewEvents(
            Observable.merge(
                Observable.just(loadSfWeatherEventPublisher)
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
        viewStateBinder.setViewApi(this)
        loadSfWeatherEventPublisher.onNext(WeatherViewEvent.LoadSfWeatherEvent)
        pressure_label_text_view.setUnderlined()
        wind_speed_label_text_view.setUnderlined()
        humidity_label_text_view.setUnderlined()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewStateBinder.unbindView()
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
    override fun setCity(text: String) {
        city_name_text_view.text = text
    }

    override fun setParentRegion(text: String) {
        parent_region_text_view.text = text
    }

    override fun setTemperature(text: String) {
        temperature_text_view.text = text
    }

    override fun setProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
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