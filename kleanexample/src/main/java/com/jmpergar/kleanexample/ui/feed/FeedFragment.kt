package com.jmpergar.kleanexample.ui.feed

import com.jmpergar.klean.ui.extension.showToast
import com.jmpergar.klean.ui.fragment.BaseFragment
import com.jmpergar.kleanexample.R

import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : BaseFragment<FeedPresenter, FeedPresenter.View>(), FeedPresenter.View {

    companion object {

        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }

    // TODO Integrate Dagger or KoDeIn??
    override val presenter: FeedPresenter = FeedProvider.provideEventsPresenter()
    override val fragmentLayout = R.layout.fragment_feed

    override fun renderFeed(feed: List<FeedElement>) {
        messageTextView.text = "Ola ke ase! " + feed.size + " elementos o ke ase! y " + (feed[0] as FeedElement.Collections).collections.size + " colleciones"
    }

    override fun renderServerError() { // TODO
        showToast("Ola ke ase! error o ke ase!")
    }

    override fun renderNetworkError() { // TODO
        showToast("Ola ke ase! error o ke ase!")
    }
}
