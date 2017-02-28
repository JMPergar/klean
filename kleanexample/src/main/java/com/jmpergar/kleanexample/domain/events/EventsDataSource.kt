package com.jmpergar.kleanexample.domain.events

import com.jmpergar.kleanexample.domain.GenericExceptions
import com.jmpergar.kleanexample.domain.events.entity.EventSummary
import com.jmpergar.kleanexample.domain.events.entity.Location
import org.funktionale.either.Disjunction

interface EventsDataSource {

    fun getCloseEvents(input: Location): Disjunction<GenericExceptions, List<EventSummary>>
}