package com.ngb.namaztime.data.repository

import androidx.lifecycle.LiveData
import com.ngb.namaztime.data.db.entities.TodayData


/*
We gonna have request params e.g. city, country from setting
It is needed  in Networkdatasource and underlying implementation
But the params are going to be omitted from repository onwards
 */
interface PrayerTimeRepository {

    suspend fun fetchTodayPrayertime(): LiveData<TodayData>

}