package com.ngb.namaztime.ui.future

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ngb.namaztime.R
import kotlinx.android.synthetic.main.future_fragment.*

class FutureFragment : Fragment(R.layout.future_fragment) {

    private lateinit var viewModel: FutureViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FutureViewModel::class.java)
        // TODO: Use the ViewModel

    }

}
