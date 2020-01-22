package com.ngb.namaztime.data.network

import androidx.lifecycle.LiveData
import com.ngb.namaztime.data.response.TodayResponse

interface PrayerTimeNetworkDataSource {

    val fetchedData : LiveData<TodayResponse>

    suspend fun fetchTodayPrayerTimeData(
        city: String,
        country: String
    )

}