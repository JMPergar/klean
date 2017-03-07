package com.jmpergar.kleanexample.data.events

import com.google.gson.GsonBuilder
import com.jmpergar.kleanexample.domain.GenericExceptions
import com.jmpergar.kleanexample.domain.events.entity.EventSummary
import com.jmpergar.kleanexample.domain.events.entity.Location
import com.jmpergar.kleanexample.domain.events.EventsDataSource
import org.funktionale.either.Disjunction
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class EventsFakeDataSource : EventsDataSource {

    // http://beta.json-generator.com/api/json/get/Ek3CO0DKG

    override fun getCloseEvents(input: Location): Disjunction<GenericExceptions, List<EventSummary>> {
        try {
            val events = getCloseEvents()
            return Disjunction.right(events.map { it -> it.domainObject })
        } catch (e: IOException) {
            return Disjunction.left(GenericExceptions.ServerError())
        }
    }

    fun getCloseEvents(): List<EventSummaryResponse> {
        // "Sun Jan 10 1982 20:17:06 GMT+0000 (UTC)"
        // "EEE MMM dd yyyy HH:mm:ss 'GMT'Z '(UTC)'"
        val gson = GsonBuilder().setDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z '(UTC)'").create()
        val retrofit = Retrofit.Builder()
                .baseUrl("http://beta.json-generator.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        val service = retrofit.create<EventsMockInterface>(EventsMockInterface::class.java)
        return service.getCloseEvents().execute().body()
    }

    interface EventsMockInterface {

        @GET("api/json/get/Ek3CO0DKG")
        fun getCloseEvents(): Call<List<EventSummaryResponse>>
    }
}

