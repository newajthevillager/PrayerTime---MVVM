package com.ngb.namaztime.data.repository

import androidx.lifecycle.LiveData
import com.ngb.namaztime.data.db.TodayDataDao
import com.ngb.namaztime.data.db.entities.TodayData
import com.ngb.namaztime.data.network.PrayerTimeNetworkDataSource
import com.ngb.namaztime.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrayerTimeRepositoryImpl(
    private val todayDataDao: TodayDataDao,
    private val prayerTimeNetworkDataSource: PrayerTimeNetworkDataSource,
    private val locationProvider: LocationProvider
) : PrayerTimeRepository {

    init {
        prayerTimeNetworkDataSource.fetchedData.observeForever {
            persistTodayData(it.todayData)
        }
    }

    // will fetch data from local db insted of directly fetching from remote
    override suspend fun fetchTodayPrayertime(): LiveData<TodayData> {
        prayerTimeNetworkDataSource.fetchTodayPrayerTimeData(
            locationProvider.getPreferredCity(),
            locationProvider.getPreferredCountry()
        )
        return withContext(Dispatchers.IO) {
            return@withContext todayDataDao.getTodaydata()
        }
    }

    private fun persistTodayData(todayData: TodayData) {
        GlobalScope.launch(Dispatchers.IO) {
            todayDataDao.upsert(todayData)
        }
    }

}