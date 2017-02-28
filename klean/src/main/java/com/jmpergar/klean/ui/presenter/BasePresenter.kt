package com.jmpergar.klean.ui.presenter

abstract class BasePresenter<T : BasePresenter.View> {

    /**
     * Represents the View component inside the Model View Presenter pattern. This interface must be used as base
     * interface for every View interface declared.
     */
    interface View

    /**
     * Returns the view configured in the presenter which real implementation is an Activity or Fragment using this
     * presenter.
     */
    /**
     * Configures the View instance used in this presenter as view.
     */
    var view: T? = null

    /**
     * Method called in the presenter lifecycle. Invoked when the component containing the presenter is initialized.
     */
    open fun initialize() {
    }

    /**
     * Method called in the presenter lifecycle. Invoked when the component containing the presenter is resumed.
     */
    open fun update() {
    }

    /**
     * Method called in the presenter lifecycle. Invoked when the component containing the presenter is paused.
     */
    open fun pause() {
    }

    /**
     * Method called in the presenter lifecycle. Invoked when the component containing the presenter is destroyed.
     */
    open fun destroy() {
    }

    /**
     * Remove the current view instance.
     */
    fun resetView() {
        view = null
    }
}