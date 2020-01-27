package com.ngb.namaztime.ui.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ngb.namaztime.data.provider.LocationProvider
import com.ngb.namaztime.data.repository.PrayerTimeRepository

class TodayViewModelFactory(
    private var prayerTimeRepository: PrayerTimeRepository,
    private var locationProvider: LocationProvider
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodayViewModel(prayerTimeRepository, locationProvider) as T
    }

}