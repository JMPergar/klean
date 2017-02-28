package com.jmpergar.kleanexample.data.collections

import com.jmpergar.kleanexample.domain.collections.entity.CollectionSummary

val CollectionSummaryResponse.domainObject: CollectionSummary
        get() = CollectionSummary(
                name,
                description,
                headerImageUrl)