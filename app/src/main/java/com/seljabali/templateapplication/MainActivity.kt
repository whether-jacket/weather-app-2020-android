package com.seljabali.templateapplication

import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import com.seljabali.templateapplication.base.BaseActivity
import com.seljabali.templateapplication.base.basic.BasicVM
import com.seljabali.templateapplication.base.basic.BasicViewEvent
import com.seljabali.templateapplication.base.basic.BasicViewState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var vm: BasicVM
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputEvents = Observable.merge(
            startButton.clicks().map { BasicViewEvent.UserHitStart() },
            stopButton.clicks().map { BasicViewEvent.UserHitStop() })
        compositeDisposable.addAll()
        vm = BasicVM()
        vm.setInputStream(inputEvents)
        compositeDisposable.addAll(vm.renderViewState().subscribe(::bindViewState))
    }

    private fun bindViewState(viewState : BasicViewState) {
        when {
            viewState.isShowing -> progressBar.visibility = View.VISIBLE
            !viewState.isShowing -> progressBar.visibility = View.GONE
        }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}
