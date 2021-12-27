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
import com.wedoapps.cricketmazza.databinding.FragmentUpcomingTestBinding

class TestUpcomingMatchFragment : Fragment(R.layout.fragment_upcoming_test) {

    private lateinit var binding: FragmentUpcomingTestBinding
    private lateinit var viewModel: CricketMazzaViewModel
    private lateinit var upcomingRecyclerAdapter: UpcomingRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpcomingTestBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        upcomingRecyclerAdapter = UpcomingRecyclerAdapter()

        getTestUpcomingData()

        binding.rvUpcomingTest.apply {
            setHasFixedSize(true)
            adapter = upcomingRecyclerAdapter
        }

    }

    private fun getTestUpcomingData() {
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
        binding.spinUpcomingTestMatches.visibility = View.GONE
        binding.rvUpcomingTest.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.spinUpcomingTestMatches.visibility = View.VISIBLE
        binding.rvUpcomingTest.visibility = View.GONE
    }

}