package com.jmpergar.klean.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.jmpergar.klean.ui.presenter.BasePresenter
import com.jmpergar.klean.ui.presenter.PresenterLifecycleLinker

abstract class BaseFragment<out T : BasePresenter<U>, U : BasePresenter.View>
    : Fragment(), PresenterLifecycleLinker.PresenterProvider, BasePresenter.View {

    private val presenterLifecycleLinker = PresenterLifecycleLinker()
    protected open val presenter: T? = null
    protected abstract val fragmentLayout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (fragmentLayout > 0) {
            return inflater.inflate(fragmentLayout, container, false)
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onPreparePresenter()
        initializePresenterLifecycle()
    }

    override fun onPause() {
        super.onPause()
        presenterLifecycleLinker.pausePresenters()
    }

    override fun onResume() {
        super.onResume()
        presenterLifecycleLinker.updatePresenters(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterLifecycleLinker.destroyPresenters()
    }

    /**
     * Called before to initialize all the presenter instances linked to the component lifecycle.
     * Override this method to configure your presenter with extra data if needed.
     */
    protected fun onPreparePresenter() {
        Log.d("BaseFragment", "-> onPreparePresenter (This method can be override but is no needed)")
    }

    protected fun initializePresenterLifecycle() {
        presenterLifecycleLinker.initialize(this, this)
    }

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
