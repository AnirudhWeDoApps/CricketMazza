package com.wedoapps.cricketmazza.Ui.Upcoming.ViewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.CricketMazzaViewModel
import com.wedoapps.cricketmazza.Ui.MainActivity
import com.wedoapps.cricketmazza.Ui.Upcoming.Adapter.UpcomingRecyclerAdapter
import com.wedoapps.cricketmazza.Utils.Constants
import com.wedoapps.cricketmazza.Utils.Resource
import com.wedoapps.cricketmazza.databinding.FragmentUpcomingT20Binding

class T20UpcomingMatchFragment : Fragment(R.layout.fragment_upcoming_t20) {

    private lateinit var binding: FragmentUpcomingT20Binding
    private lateinit var viewModel: CricketMazzaViewModel
    private lateinit var upcomingRecyclerAdapter: UpcomingRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpcomingT20Binding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        upcomingRecyclerAdapter = UpcomingRecyclerAdapter()

        getT20UpcomingData()

        binding.rvUpcomingT20.apply {
            setHasFixedSize(true)
            adapter = upcomingRecyclerAdapter
        }
    }

    private fun getT20UpcomingData() {
        viewModel.getUpcomingMatches().observe(requireActivity(), { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoading()
                    upcomingRecyclerAdapter.differ.submitList(response.data)
                }

                is Resource.Error -> {
                    Log.d(Constants.TAG, "getAllUpcomingData: ${response.message}")
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }
        })
    }

    private fun hideLoading() {
        binding.spinUpcomingT20Matches.visibility = View.GONE
        binding.rvUpcomingT20.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.spinUpcomingT20Matches.visibility = View.VISIBLE
        binding.rvUpcomingT20.visibility = View.GONE
    }

}