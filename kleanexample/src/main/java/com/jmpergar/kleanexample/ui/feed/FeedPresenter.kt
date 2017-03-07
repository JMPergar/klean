package com.jmpergar.kleanexample.ui.feed

import com.jmpergar.kleanexample.domain.events.entity.EventSummary
import com.jmpergar.kleanexample.domain.events.entity.Location
import com.jmpergar.kleanexample.domain.GenericExceptions
import com.jmpergar.kleanexample.domain.collections.entity.CollectionSummary
import com.jmpergar.klean.domain.UseCase
import com.jmpergar.klean.ui.presenter.BasePresenter
import com.jmpergar.kleanexample.ui.feed.FeedElement
import org.funktionale.either.Disjunction
import org.funktionale.validation.validate
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.uiThread

class FeedPresenter(
        val getCloseEventsUseCase: UseCase<Location, List<EventSummary>, GenericExceptions>,
        val getCollectionsUseCase: UseCase<Location, List<CollectionSummary>, GenericExceptions>)
    : BasePresenter<FeedPresenter.View>() {

    interface View : BasePresenter.View {

        fun renderFeed(feed: List<FeedElement>)
        fun renderServerError()
        fun renderNetworkError()
    }

    override fun update() {
        initLoadData()
    }

    private fun initLoadData() {
        val location = Location(0.0, 0.0) // TODO Add collaborator to obtain geolocation
        doAsync {
            val closeEvents = getCloseEvents(location)
            val collections = getCollections(location)
            // TODO Optimize to avoid block thread
            val result = generateFeedElementsList(closeEvents.get(), collections.get())
            uiThread { renderFeedResult(result) }
        }
    }

    private fun getCloseEvents(location: Location) = doAsyncResult { getCloseEventsUseCase.execute(location) }

    private fun getCollections(location: Location) = doAsyncResult { getCollectionsUseCase.execute(location) }

    private fun generateFeedElementsList(
            events: Disjunction<GenericExceptions, List<EventSummary>>,
            collections: Disjunction<GenericExceptions, List<CollectionSummary>>)
            : Disjunction<List<GenericExceptions>, List<FeedElement>> {
        return validate(events, collections) {
            events, collections ->
            listOf(FeedElement.Collections(collections)) + events.map { FeedElement.Event(it) }
        }
    }

    private fun renderFeedResult(result: Disjunction<List<GenericExceptions>, List<FeedElement>>): Any? =
            when (result) {
                is Disjunction.Left -> manageExceptions(result.swap().get())
                is Disjunction.Right -> result.map { view?.renderFeed(it) }
            }

    private fun manageExceptions(exceptions: List<GenericExceptions>) =
            when (exceptions[0]) {
                is GenericExceptions.NetworkError -> view?.renderNetworkError()
                is GenericExceptions.ServerError -> view?.renderServerError()
            }
}