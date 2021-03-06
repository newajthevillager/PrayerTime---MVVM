package com.ngb.namaztime.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ngb.namaztime.data.response.TodayResponse
import com.ngb.namaztime.utils.NoInternetConnectionException

class PrayerTimeNetworkDataSourceImpl(
    val prayerTimeApiService: PrayerTimeApiService
) : PrayerTimeNetworkDataSource {

    private val fetchedTodayData = MutableLiveData<TodayResponse>()

    override val fetchedData: LiveData<TodayResponse>
        get() = fetchedTodayData

    override suspend fun fetchTodayPrayerTimeData(city: String, country: String) {
        try {
            val  response = prayerTimeApiService.getTodayPrayerTime(city, country, 8).await()
            fetchedTodayData.postValue(response)
            Log.d("LLL", "city $city, Country $country")
        } catch (e: NoInternetConnectionException) {
        }
    }
}