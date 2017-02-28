package com.jmpergar.klean.ui.presenter

/**
 * This class is only to satisfy the typed of base activity when we don't need a presenter
 */
class WithoutPresenter : BasePresenter<WithoutPresenter.View>() {

    interface View : BasePresenter.View
}