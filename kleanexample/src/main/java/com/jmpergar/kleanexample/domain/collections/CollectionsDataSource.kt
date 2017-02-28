package com.jmpergar.kleanexample.domain.collections

import com.jmpergar.kleanexample.domain.GenericExceptions
import com.jmpergar.kleanexample.domain.collections.entity.CollectionSummary
import com.jmpergar.kleanexample.domain.events.entity.Location
import org.funktionale.either.Disjunction

interface CollectionsDataSource {

    fun getCloseCollections(input: Location): Disjunction<GenericExceptions, List<CollectionSummary>>
}