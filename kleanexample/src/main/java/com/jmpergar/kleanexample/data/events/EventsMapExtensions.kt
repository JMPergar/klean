package com.jmpergar.kleanexample.data.events

import com.jmpergar.kleanexample.domain.events.entity.EventSummary
import com.jmpergar.kleanexample.domain.events.entity.EventType
import com.jmpergar.kleanexample.domain.events.entity.Location
import com.jmpergar.kleanexample.domain.events.entity.Place

val EventSummaryResponse.domainObject: EventSummary
    get() = EventSummary(
            name,
            place.domainObject,
            startDate,
            endDate,
            ratting,
            isSelection,
            isFavorite,
            headerImageUrl,
            price,
            getEventType(eventType),
            location.domainObject)

val LocationResponse.domainObject: Location
    get() = Location(latitude, longitude)

val PlaceResponse.domainObject: Place
    get() = Place(name)

private fun getEventType(eventType: String): EventType {
    when(eventType) {
        "THEATER" -> return EventType.THEATER
        "MONOLOGUE" -> return EventType.MONOLOGUE
        "MUSICAL" -> return EventType.MUSICAL
        "CONCERT" -> return EventType.CONCERT
        else -> {
            throw IllegalArgumentException("Unknown EventType")
        }
    }
}