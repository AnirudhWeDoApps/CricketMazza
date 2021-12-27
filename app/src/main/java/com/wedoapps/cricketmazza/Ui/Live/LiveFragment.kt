package com.wedoapps.cricketmazza.Ui.Live

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.CricketMazzaViewModel
import com.wedoapps.cricketmazza.Ui.Live.Adapter.LiveMatchRecyclerAdapter
import com.wedoapps.cricketmazza.Ui.Live.Adapter.LiveUpcomingMatchRecyclerAdapter
import com.wedoapps.cricketmazza.Ui.MainActivity
import com.wedoapps.cricketmazza.Utils.Constants.TAG
import com.wedoapps.cricketmazza.Utils.Resource
import com.wedoapps.cricketmazza.databinding.FragmentLiveBinding

class LiveFragment : Fragment(R.layout.fragment_live) {

    private lateinit var binding: FragmentLiveBinding
    private lateinit var liveMatchAdapter: LiveMatchRecyclerAdapter
    private lateinit var upcomingMatchRecyclerAdapter: LiveUpcomingMatchRecyclerAdapter
    private lateinit var viewModel: CricketMazzaViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLiveBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel

        liveMatchAdapter = LiveMatchRecyclerAdapter()
        upcomingMatchRecyclerAdapter = LiveUpcomingMatchRecyclerAdapter()

        liveMatchData()
        upcomingLiveMatchData()

    }


    private fun liveMatchData() {
        viewModel.getLiveMatch().observe(requireActivity(), { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoading()
                    liveMatchAdapter.differ.submitList(response.data)
                }

                is Resource.Error -> {
                    Log.d(TAG, "liveMatchData: ${response.message}")
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }
        })

        binding.rvLivematch.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = liveMatchAdapter
        }

    }

    private fun upcomingLiveMatchData() {
        viewModel.getUpcomingMatches().observe(requireActivity(), { response ->
            when (response) {
                is Resource.Success -> {
                    hideUpcomingLoading()
                    upcomingMatchRecyclerAdapter.differ.submitList(response.data)
                }

                is Resource.Error -> {
                    Log.d(TAG, "upcomingLiveMatchData: ${response.message}")
                }

                is Resource.Loading -> {
                    showUpcomingLoading()
                }
            }
        })

        binding.rvLiveUpcomingMatch.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = upcomingMatchRecyclerAdapter
        }

    }

    private fun hideLoading() {
        binding.spinLiveMatches.visibility = View.GONE
        binding.rvLivematch.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.spinLiveMatches.visibility = View.VISIBLE
        binding.rvLivematch.visibility = View.INVISIBLE
    }

    private fun hideUpcomingLoading() {
        binding.spinUpcomingMatches.visibility = View.GONE
        binding.rvLiveUpcomingMatch.visibility = View.VISIBLE
    }

    private fun showUpcomingLoading() {
        binding.spinUpcomingMatches.visibility = View.VISIBLE
        binding.rvLiveUpcomingMatch.visibility = View.INVISIBLE
    }
}