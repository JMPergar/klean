package com.jmpergar.kleanexample.ui.feed

import com.jmpergar.kleanexample.data.collections.CollectionsFakeDataSource
import com.jmpergar.kleanexample.data.events.EventsFakeDataSource
import com.jmpergar.kleanexample.domain.GenericExceptions
import com.jmpergar.kleanexample.domain.events.entity.EventSummary
import com.jmpergar.kleanexample.domain.events.entity.Location
import com.jmpergar.kleanexample.domain.collections.entity.CollectionSummary
import com.jmpergar.kleanexample.domain.collections.GetCollectionsUseCase
import com.jmpergar.kleanexample.domain.events.GetCloseEventsUseCase
import com.jmpergar.kleanexample.domain.collections.CollectionsDataSource
import com.jmpergar.kleanexample.domain.events.EventsDataSource
import com.jmpergar.klean.domain.UseCase

object FeedProvider {

    fun provideEventsPresenter(
            getCloseEventsUseCase: UseCase<Location, List<EventSummary>, GenericExceptions> = provideGetCloseEventsUseCase(),
            getCollectionsUseCase: UseCase<Location, List<CollectionSummary>, GenericExceptions> = provideGetCollectionsUseCase()
    )
            : FeedPresenter {
        return FeedPresenter(getCloseEventsUseCase, getCollectionsUseCase)
    }

    private fun provideGetCloseEventsUseCase
            (eventsEventsDataSource: EventsDataSource = provideEventsDataSource())
            : UseCase<Location, List<EventSummary>, GenericExceptions> {
        return GetCloseEventsUseCase(eventsEventsDataSource)
    }

    private fun provideGetCollectionsUseCase
            (collectionsDataSource: CollectionsDataSource = provideCollectionsDataSource())
            : UseCase<Location, List<CollectionSummary>, GenericExceptions> {
        return GetCollectionsUseCase(collectionsDataSource)
    }

    // TODO In the future this provide will need to be singleton (Use object)
    private fun provideEventsDataSource(): EventsDataSource {
        return EventsFakeDataSource()
    }

    // TODO In the future this provide will need to be singleton (Use object)
    private fun provideCollectionsDataSource(): CollectionsDataSource {
        return CollectionsFakeDataSource()
    }
}