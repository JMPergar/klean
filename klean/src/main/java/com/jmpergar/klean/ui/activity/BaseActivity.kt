package com.lorcapp.klean.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<out T : BasePresenter<U>, U : BasePresenter.View>
    : AppCompatActivity(), PresenterLifecycleLinker.PresenterProvider, BasePresenter.View {

    private val presenterLifecycleLinker: PresenterLifecycleLinker = PresenterLifecycleLinker()
    protected open val presenter: T? = null
    protected abstract val activityLayout: Int

    // region Lifecycle methods

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityLayout)
        presenterLifecycleLinker.initialize(this, this)
    }

    override fun onResume() {
        super.onResume()
        presenterLifecycleLinker.updatePresenters(this)
    }

    public override fun onPause() {
        super.onPause()
        presenterLifecycleLinker.pausePresenters()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterLifecycleLinker.destroyPresenters()
    }

    // endregion Lifecycle methods

    override val presenters: List<BasePresenter<BasePresenter.View>>
        get() {
            if (presenter != null) {
                @Suppress("UNCHECKED_CAST")
                return listOf(presenter as BasePresenter<BasePresenter.View>)
            } else {
                return listOf()
            }
        }
}