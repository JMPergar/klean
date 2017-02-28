package com.jmpergar.kleanexample.domain.events

import com.jmpergar.kleanexample.domain.GenericExceptions
import com.jmpergar.kleanexample.domain.events.entity.EventSummary
import com.jmpergar.kleanexample.domain.events.entity.Location
import com.jmpergar.klean.domain.UseCase
import org.funktionale.either.Disjunction

class GetCloseEventsUseCase(
        val eventsEventsDataSource: EventsDataSource)
    : UseCase<Location, List<EventSummary>, GenericExceptions> {

    override fun execute(input: Location): Disjunction<GenericExceptions, List<EventSummary>> {

        return eventsEventsDataSource.getCloseEvents(input)
    }
}