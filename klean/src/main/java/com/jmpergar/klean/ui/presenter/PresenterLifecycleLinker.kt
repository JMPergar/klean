package com.lorcapp.klean.ui

class PresenterLifecycleLinker {

    private var presenters = listOf<BasePresenter<BasePresenter.View>>()

    fun initialize(presenterProvider: PresenterProvider, view: BasePresenter.View) {
        presenters = presenterProvider.presenters
        setView(view)

        initializePresenters()
    }

    /**
     * Initializes all the already registered presenters lifecycle.
     */
    private fun initializePresenters() {
        for (presenter in presenters) { presenter.initialize() }
    }

    /**
     * Updates all the already registered presenters lifecycle and updates the view instance
     * associated to these presenters.

     * @param view to be updated for every registered presenter.
     */
    fun updatePresenters(view: BasePresenter.View?) {
        if (view == null) {
            throw IllegalArgumentException("The view instance used to update the presenters can't be null")
        }

        presenters.forEach {
            it.view = view
            it.update()
        }
    }

    /**
     * Pauses all the already registered presenters lifecycle.
     */
    fun pausePresenters() {
        presenters.forEach {
            it.pause()
            it.resetView()
        }
    }

    /**
     * Destroys all the already registered presenters lifecycle.
     */
    fun destroyPresenters() {
        presenters.forEach { it.destroy() }
    }

    interface PresenterProvider {

        val presenters: List<BasePresenter<BasePresenter.View>>
    }

    private fun setView(view: BasePresenter.View) {
        for (presenter in presenters) { presenter.view = view }
    }
}