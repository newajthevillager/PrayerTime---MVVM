package com.ngb.namaztime.ui.today

import androidx.lifecycle.ViewModel
import com.ngb.namaztime.data.provider.LocationProvider
import com.ngb.namaztime.data.repository.PrayerTimeRepository
import com.ngb.namaztime.data.repository.PrayerTimeRepositoryImpl
import com.ngb.namaztime.utils.lazyDeferred

class TodayViewModel(
    prayerTimeRepository: PrayerTimeRepository,
    locationProvider: LocationProvider
) : ViewModel() {

    val prayerTime by lazyDeferred {
        prayerTimeRepository.fetchTodayPrayertime()
    }

    val cityName by lazyDeferred {
        prayerTimeRepository.fethCity()
    }

    val countryName by lazyDeferred {
        prayerTimeRepository.fetchCountry()
    }

}
