package com.lorcapp.klean.ui

import com.lorcapp.klean.ui.BasePresenter

/**
 * This class is only to satisfy the typed of base activity when we don't need a presenter
 */
class WithoutPresenter : BasePresenter<WithoutPresenter.View>() {

    interface View : BasePresenter.View
}