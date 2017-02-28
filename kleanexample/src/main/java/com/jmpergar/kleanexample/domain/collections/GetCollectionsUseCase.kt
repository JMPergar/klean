package com.jmpergar.kleanexample.domain.collections

import com.jmpergar.kleanexample.domain.GenericExceptions
import com.jmpergar.kleanexample.domain.collections.entity.CollectionSummary
import com.jmpergar.kleanexample.domain.events.entity.Location
import com.jmpergar.klean.domain.UseCase
import org.funktionale.either.Disjunction

class GetCollectionsUseCase(val collectionsDataSource: CollectionsDataSource)
    : UseCase<Location, List<CollectionSummary>, GenericExceptions> {

    override fun execute(input: Location): Disjunction<GenericExceptions, List<CollectionSummary>> {

        return collectionsDataSource.getCloseCollections(input)
    }
}