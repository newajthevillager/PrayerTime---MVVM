package com.ngb.namaztime.ui.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ngb.namaztime.data.repository.PrayerTimeRepository
import com.ngb.namaztime.data.repository.PrayerTimeRepositoryImpl

class TodayViewModelFactory(
    private var prayerTimeRepository: PrayerTimeRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodayViewModel(prayerTimeRepository as PrayerTimeRepositoryImpl) as T
    }

}