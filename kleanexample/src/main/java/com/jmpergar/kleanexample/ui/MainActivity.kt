package com.jmpergar.kleanexample.ui

import android.support.v4.app.Fragment
import com.jmpergar.kleanexample.ui.feed.FeedFragment
import com.jmpergar.klean.ui.activity.SingleFragmentActivity
import com.jmpergar.klean.ui.presenter.WithoutPresenter

class MainActivity : SingleFragmentActivity<WithoutPresenter, WithoutPresenter.View>(), WithoutPresenter.View {

    override fun initializeFragment(): Fragment {
        return FeedFragment.newInstance()
    }
}
