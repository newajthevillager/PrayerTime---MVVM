package com.ngb.namaztime.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ngb.namaztime.data.response.TodayResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "http://api.aladhan.com/v1/"

interface PrayerTimeApiService {

    // http://api.aladhan.com/v1/timingsByCity?
    // city=chittagong&country=Bangladesh&method=8

    @GET("timingsByCity")
    fun getTodayPrayerTime(
        @Query("city")
        city: String,
        @Query("country")
        country: String,
        // TODO will be changed to interceptor
        @Query("method")
        method: Int
    ) : Deferred<TodayResponse>

    companion object {
        operator fun invoke() : PrayerTimeApiService {

            val okHttpClient = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PrayerTimeApiService::class.java)

        }
    }


}