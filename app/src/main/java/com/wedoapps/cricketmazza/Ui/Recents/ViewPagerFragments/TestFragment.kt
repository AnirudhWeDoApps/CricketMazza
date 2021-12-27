package com.wedoapps.cricketmazza.Ui.Recents.ViewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.CricketMazzaViewModel
import com.wedoapps.cricketmazza.Ui.MainActivity
import com.wedoapps.cricketmazza.Ui.Recents.Adapter.RecentRecyclerAdapter
import com.wedoapps.cricketmazza.Utils.Constants.TAG
import com.wedoapps.cricketmazza.Utils.Resource
import com.wedoapps.cricketmazza.databinding.FragmentTestBinding

class TestFragment : Fragment(R.layout.fragment_test) {

    private lateinit var binding: FragmentTestBinding
    private lateinit var recentRecyclerAdapter: RecentRecyclerAdapter
    private lateinit var viewModel: CricketMazzaViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTestBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel

        recentRecyclerAdapter = RecentRecyclerAdapter()
        recentData()

        binding.rvRecentTest.apply {
            setHasFixedSize(true)
            adapter = recentRecyclerAdapter
        }

    }

    private fun recentData() {
        viewModel.getMatch().observe(requireActivity(), { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoading()
                    recentRecyclerAdapter.differ.submitList(response.data)
                }

                is Resource.Error -> {
                    Log.d(TAG, "recentTestData: ${response.message}")
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }
        })
    }

    private fun hideLoading() {
        binding.spinRecentTestMatches.visibility = View.GONE
        binding.rvRecentTest.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.spinRecentTestMatches.visibility = View.VISIBLE
        binding.rvRecentTest.visibility = View.GONE
    }
}