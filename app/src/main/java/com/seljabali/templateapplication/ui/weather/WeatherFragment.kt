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
        loadWeatherButton.setOnClickListener {
            loadSfWeatherEventPublisher.onNext(WeatherViewEvent.LoadSfWeatherEvent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewStateBinder.unbindView()
    }

    /**
     * MVVM Events
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
    override fun setText(text: String) {
        helloWorldTextView.text = text
    }

    override fun setProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }
}