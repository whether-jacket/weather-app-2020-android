package com.seljabali.core.mvi

import android.os.Bundle
import androidx.annotation.CallSuper
import com.orhanobut.logger.Logger
import com.seljabali.core.activityfragment.nontoolbar.BaseFragment
import com.seljabali.core.modules.RxProvider
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.error.DefinitionOverrideException
import org.koin.core.module.Module

abstract class BaseMviFragment<ViewEvent: BaseViewEvent, ViewState : BaseViewState, SideEffect: BaseSideEffect>(
    private val module: Module? = null
) : BaseFragment() {

    protected val rxProvider: RxProvider by inject()
    protected abstract val viewModel: BaseViewModel<ViewEvent, ViewState, SideEffect>
    private val mviCompositeDisposable = CompositeDisposable()

    init {
        try {
            module?.let { loadKoinModules(it) }
        } catch (e: DefinitionOverrideException) {
            Logger.e(e, e.message?: "")
        }
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToViewModel()
    }

    @CallSuper
    override fun onDestroy() {
        module?.let { unloadKoinModules(it) }
        mviCompositeDisposable.clear()
        super.onDestroy()
    }

    @CallSuper
    protected open fun subscribeToViewModel() {
        val viewStateDisposable = viewModel.viewStateEvents
            .observeOn(rxProvider.uiScheduler())
            .subscribe({ onViewStateEvent(it) }, { Logger.e(it, it.message ?: "") })
        mviCompositeDisposable.add(viewStateDisposable)

        val sideEffectDisposable = viewModel.sideEffects
            .observeOn(rxProvider.uiScheduler())
            .subscribe({ onSideEffectEvent(it) }, { Logger.e(it, it.message ?: "") })
        mviCompositeDisposable.add(sideEffectDisposable)
    }

    protected abstract fun onViewStateEvent(viewState: ViewState)

    protected abstract fun onSideEffectEvent(sideEffect: SideEffect)

}
