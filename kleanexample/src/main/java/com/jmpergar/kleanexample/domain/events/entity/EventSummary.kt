package com.jmpergar.kleanexample.domain.events.entity

import java.util.Date

data class EventSummary(
        val name: String,
        val place: Place,
        val startDate: Date,
        val endDate: Date,
        val ratting: Float,
        val isSelection: Boolean,
        val isFavorite: Boolean,
        val headerImageUrl: String,
        val price: Float,
        val eventType: EventType,
        val location: Location
)
