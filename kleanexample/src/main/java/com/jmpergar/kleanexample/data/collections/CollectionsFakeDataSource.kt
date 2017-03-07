package com.jmpergar.kleanexample.data.collections

import com.jmpergar.kleanexample.domain.GenericExceptions
import com.jmpergar.kleanexample.domain.collections.entity.CollectionSummary
import com.jmpergar.kleanexample.domain.events.entity.Location
import com.jmpergar.kleanexample.domain.collections.CollectionsDataSource
import org.funktionale.either.Disjunction
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class CollectionsFakeDataSource : CollectionsDataSource {

    // http://beta.json-generator.com/api/json/get/E1dyw-_Ff

    override fun getCloseCollections(input: Location): Disjunction<GenericExceptions, List<CollectionSummary>> {
        try {
            val events = getCloseCollections()
            return Disjunction.right(events.map { it -> it.domainObject })
        } catch (e: IOException) {
            return Disjunction.left(GenericExceptions.ServerError())
        }
    }

    fun getCloseCollections(): List<CollectionSummaryResponse> {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://beta.json-generator.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create<CollectionsMockInterface>(CollectionsMockInterface::class.java)
        return service.getCloseEvents().execute().body()
    }

    interface CollectionsMockInterface {

        @GET("api/json/get/E1dyw-_Ff")
        fun getCloseEvents(): Call<List<CollectionSummaryResponse>>
    }
}

