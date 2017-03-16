package com.jmpergar.klean.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.jmpergar.klean.R
import com.jmpergar.klean.ui.presenter.BasePresenter
import kotlinx.android.synthetic.main.view_toolbar.*
import com.jmpergar.klean.ui.extension.loadUrl

abstract class SingleFragmentActivity<out T : BasePresenter<U>, U : BasePresenter.View> : BaseActivity<T, U>() {

    companion object {

        val TAG_FRAGMENT = "SingleActivityFragment"
    }

    override val activityLayout: Int
        get() = R.layout.activity_single_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpToolbar()
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, initializeFragment(), TAG_FRAGMENT)
                    .commit()
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    protected abstract fun initializeFragment(): Fragment

    fun setAvatarClickListener(f: () -> Unit) {
        toolbarAvatar.setOnClickListener { f() }
    }

    fun setAvatarImageUrl(url: String) {
        toolbarAvatar.loadUrl(url)
    }

    fun setAvatarEnable(enable: Boolean) {
        toolbarAvatar.visibility = if (enable) { View.VISIBLE } else { View.GONE }
    }
}