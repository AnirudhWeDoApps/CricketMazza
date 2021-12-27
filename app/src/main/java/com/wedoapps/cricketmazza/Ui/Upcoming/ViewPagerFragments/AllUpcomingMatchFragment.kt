package com.wedoapps.cricketmazza.Ui.Upcoming.ViewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.CricketMazzaViewModel
import com.wedoapps.cricketmazza.Ui.MainActivity
import com.wedoapps.cricketmazza.Ui.Upcoming.Adapter.UpcomingRecyclerAdapter
import com.wedoapps.cricketmazza.Utils.Constants.TAG
import com.wedoapps.cricketmazza.Utils.Resource
import com.wedoapps.cricketmazza.databinding.FragmentUpcomingAllBinding

class AllUpcomingMatchFragment : Fragment(R.layout.fragment_upcoming_all) {

    private lateinit var binding: FragmentUpcomingAllBinding
    private lateinit var viewModel: CricketMazzaViewModel
    private lateinit var upcomingRecyclerAdapter: UpcomingRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpcomingAllBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        upcomingRecyclerAdapter = UpcomingRecyclerAdapter()

        getAllUpcomingData()

        binding.rvUpcomingAll.apply {
            setHasFixedSize(true)
            adapter = upcomingRecyclerAdapter
        }

    }

    private fun getAllUpcomingData() {
        viewModel.getUpcomingMatches().observe(requireActivity(), { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoading()
                    upcomingRecyclerAdapter.differ.submitList(response.data)
                }

                is Resource.Error -> {
                    Log.d(TAG, "getAllUpcomingData: ${response.message}")
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }
        })
    }

    private fun hideLoading() {
        binding.spinUpcomingAllMatches.visibility = View.GONE
        binding.rvUpcomingAll.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.spinUpcomingAllMatches.visibility = View.VISIBLE
        binding.rvUpcomingAll.visibility = View.GONE
    }
}