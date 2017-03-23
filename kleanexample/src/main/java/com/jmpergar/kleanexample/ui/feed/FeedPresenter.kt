package com.jmpergar.kleanexample.ui.feed

import com.jmpergar.klean.core.Future
import com.jmpergar.kleanexample.domain.events.entity.EventSummary
import com.jmpergar.kleanexample.domain.events.entity.Location
import com.jmpergar.kleanexample.domain.GenericExceptions
import com.jmpergar.kleanexample.domain.collections.entity.CollectionSummary
import com.jmpergar.klean.domain.UseCase
import com.jmpergar.klean.ui.presenter.BasePresenter
import org.funktionale.either.Disjunction
import org.funktionale.validation.validate

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

        val closeEventsFuture = getCloseEvents(location)
        val collectionsFuture = getCollections(location)

        /**
         * Instead of
         *
         * closeEventsFuture.flatMap {
         *      closeEvents -> collectionsFuture.map {
         *          collections -> generateFeedElementsList(closeEvents, collections)
         *      }
         * }
         *
         * We've implemented forComprehension method to reduce boiler plate
         */

        val result = forComprehension(closeEventsFuture, collectionsFuture) {
            closeEvents, collections -> generateFeedElementsList(closeEvents, collections)
        }

        result.onComplete {
            renderFeedResult(it)
        }
    }

    // TODO Fin better name for this method
    fun <T, K, R> forComprehension(f1: Future<T>, f2: Future<K>, function: (T, K) -> R): Future<R> {
        return f1.flatMap { it1 -> f2.map { it2 -> function(it1, it2) } }
    }

    private fun getCloseEvents(location: Location) = Future { getCloseEventsUseCase.execute(location) }

    private fun getCollections(location: Location) = Future { getCollectionsUseCase.execute(location) }

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