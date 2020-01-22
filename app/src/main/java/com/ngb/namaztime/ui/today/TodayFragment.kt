package com.ngb.namaztime.ui.today

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.ngb.namaztime.R
import com.ngb.namaztime.data.db.PrayerTimeDatabase
import com.ngb.namaztime.data.network.ConnectivityInterceptorImpl
import com.ngb.namaztime.data.network.PrayerTimeApiService
import com.ngb.namaztime.data.network.PrayerTimeNetworkDataSourceImpl
import com.ngb.namaztime.data.repository.PrayerTimeRepositoryImpl
import com.ngb.namaztime.utils.NoInternetConnectionException
import kotlinx.android.synthetic.main.today_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodayFragment : Fragment() {

    private lateinit var viewModel: TodayViewModel
    private lateinit var viewModelFactory: TodayViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        var dao = PrayerTimeDatabase(this.context!!).todayDataDao()
        var prayerTimeApiService = PrayerTimeApiService(ConnectivityInterceptorImpl(this.context!!))
        var prayerTimeNetworkDataSource = PrayerTimeNetworkDataSourceImpl(prayerTimeApiService)
        var repository = PrayerTimeRepositoryImpl(dao, prayerTimeNetworkDataSource)

        viewModelFactory = TodayViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodayViewModel::class.java)


//        repository.data.observe(this, Observer {
//            prayerTv.text = it.toString()
//        })

        GlobalScope.launch(Dispatchers.Main) {
            viewModel.prayerTime.await().observe(this@TodayFragment, Observer {
                prayerTv.text = it.toString()
            })
        }

    }

}
