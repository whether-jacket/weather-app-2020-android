package com.seljabali.templateapplication.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seljabali.core.mvvm.BaseMvvmFragment
import com.seljabali.templateapplication.R
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_weather_landing_page.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : BaseMvvmFragment<WeatherViewEvent, WeatherViewState, WeatherSideEffect>(
    module = weatherModule
) {
    companion object {
        val TAG: String = WeatherFragment::class.java.simpleName
        fun newInstance() = WeatherFragment()
    }

    private val loadSfWeatherEventPublisher =
        BehaviorSubject.create<WeatherViewEvent.LoadSfWeatherEvent>()

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
        loadWeatherButton.setOnClickListener {
            loadSfWeatherEventPublisher.onNext(WeatherViewEvent.LoadSfWeatherEvent)
        }
    }

    // TODO: Have ViewBinder handle ViewState
    override fun onViewStateEvent(viewState: WeatherViewState) {
        with (viewState) {
            val weatherSummary = "Weather in SF: ${currentTemperature} C"
            helloWorldTextView.text = weatherSummary

            progressBar.visibility = if (isLoadingTemperature) View.VISIBLE else View.INVISIBLE
        }
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
}