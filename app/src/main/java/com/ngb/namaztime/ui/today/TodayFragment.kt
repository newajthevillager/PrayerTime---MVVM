package com.ngb.namaztime.ui.today

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ngb.namaztime.R
import com.ngb.namaztime.data.network.PrayerTimeApiService
import kotlinx.android.synthetic.main.today_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodayFragment : Fragment() {

    private lateinit var viewModel: TodayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodayViewModel::class.java)

        var prayerTimeApiService = PrayerTimeApiService()

        GlobalScope.launch(Dispatchers.Main) {
            var  response = prayerTimeApiService.getTodayPrayerTime("Chittagong", "Bangladesh", 8).await()
            prayerTv.setText(response.todayData.toString())
        }

    }

}
