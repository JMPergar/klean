package com.jmpergar.kleanexample.ui.feed

import com.jmpergar.kleanexample.domain.events.entity.EventSummary as DomainEventSummary
import com.jmpergar.kleanexample.domain.collections.entity.CollectionSummary

sealed class FeedElement {

    class Event(val event: com.jmpergar.kleanexample.domain.events.entity.EventSummary) : FeedElement()
    class Collections(val collections: List<CollectionSummary>): FeedElement()
}
