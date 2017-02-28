package com.jmpergar.kleanexample.data.events

import java.util.*

data class EventSummaryResponse(
        val name: String,
        val place: PlaceResponse,
        val startDate: Date,
        val endDate: Date,
        val ratting: Float,
        val isSelection: Boolean,
        val isFavorite: Boolean,
        val headerImageUrl: String,
        val price: Float,
        val eventType: String,
        val location: LocationResponse
)

data class PlaceResponse(val name: String)

data class LocationResponse(val latitude: Double, val longitude: Double)
