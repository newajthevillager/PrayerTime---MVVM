package com.ngb.namaztime.ui.today

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.LocationServices
import com.ngb.namaztime.R
import com.ngb.namaztime.data.db.PrayerTimeDatabase
import com.ngb.namaztime.data.network.ConnectivityInterceptorImpl
import com.ngb.namaztime.data.network.PrayerTimeApiService
import com.ngb.namaztime.data.network.PrayerTimeNetworkDataSourceImpl
import com.ngb.namaztime.data.provider.LocationProviderImpl
import com.ngb.namaztime.data.repository.PrayerTimeRepositoryImpl
import kotlinx.android.synthetic.main.today_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TodayFragment : Fragment(R.layout.today_fragment) {

    private lateinit var viewModel: TodayViewModel
    private lateinit var viewModelFactory: TodayViewModelFactory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var dao = PrayerTimeDatabase(this.context!!).todayDataDao()
        var prayerTimeApiService = PrayerTimeApiService(ConnectivityInterceptorImpl(this.context!!))
        var prayerTimeNetworkDataSource = PrayerTimeNetworkDataSourceImpl(prayerTimeApiService)
        var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.context!!)
        var locationprovider = LocationProviderImpl(this.context!!, fusedLocationProviderClient)
        var repository = PrayerTimeRepositoryImpl(dao, prayerTimeNetworkDataSource, locationprovider)

        viewModelFactory = TodayViewModelFactory(repository, locationprovider)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodayViewModel::class.java)

        // should not use globalscope inside fragment/activity
        // TODO change scope
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.prayerTime.await().observe(this@TodayFragment, Observer { todayData ->
                if (todayData == null) return@Observer

                pbToday.visibility = View.GONE
                updateDateAndTimeZone(
                    todayData.date.readable,
                    todayData.date.gregorian.weekday.en,
                    todayData.meta.timezone
                )
                updateTimings(
                    todayData.timings.fajr,
                    todayData.timings.dhuhr,
                    todayData.timings.asr,
                    todayData.timings.maghrib,
                    todayData.timings.isha
                )
            })
            viewModel.cityName.await().observe(this@TodayFragment, Observer {
                updateCity(it)
            })
            viewModel.countryName.await().observe(this@TodayFragment, Observer {
                updateCountry(it)
            })
        }

    }

    private fun updateCity(city: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = city
    }

    private fun updateCountry(country: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = country
    }

    private fun updateDateAndTimeZone(date: String, day: String, timezone: String) {
        tvTodayDate.text = date
        tvTodayDay.text = day
        tvTodayTimeZone.text = "Time zone : $timezone"
    }

    private fun updateTimings(
        fajr: String,
        juhar: String,
        asar: String,
        magriv: String,
        esha: String
    ) {
        tvTodayFajr.text = "Fajar : $fajr"
        tvTodayJuhar.text = "Juhar : $juhar"
        tvTodayAsr.text = "Asar : $asar"
        tvTodayMagriv.text = "Magriv : $magriv"
        tvTodayEsha.text = "Esha : $esha"
    }

}
