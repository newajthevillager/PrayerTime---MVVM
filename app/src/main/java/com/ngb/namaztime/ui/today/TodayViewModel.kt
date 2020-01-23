package com.ngb.namaztime.ui.today

import androidx.lifecycle.ViewModel
import com.ngb.namaztime.data.repository.PrayerTimeRepository
import com.ngb.namaztime.data.repository.PrayerTimeRepositoryImpl
import com.ngb.namaztime.utils.lazyDeferred

class TodayViewModel(
    prayerTimeRepository: PrayerTimeRepository
) : ViewModel() {

    val prayerTime by lazyDeferred {
        prayerTimeRepository.fetchTodayPrayertime()
    }

}
