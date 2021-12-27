package com.wedoapps.cricketmazza.Ui.Recents.ViewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.CricketMazzaViewModel
import com.wedoapps.cricketmazza.Ui.MainActivity
import com.wedoapps.cricketmazza.Ui.Recents.Adapter.RecentRecyclerAdapter
import com.wedoapps.cricketmazza.Utils.Constants
import com.wedoapps.cricketmazza.Utils.Resource
import com.wedoapps.cricketmazza.databinding.FragmentT10Binding

class T10Fragment : Fragment(R.layout.fragment_t10) {

    private lateinit var binding: FragmentT10Binding
    private lateinit var recentRecyclerAdapter: RecentRecyclerAdapter
    private lateinit var viewModel: CricketMazzaViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentT10Binding.bind(view)

        viewModel = (activity as MainActivity).viewModel

        recentRecyclerAdapter = RecentRecyclerAdapter()
        recentData()

        binding.rvRecentT10.apply {
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
                    Log.d(Constants.TAG, "recentTestData: ${response.message}")
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }
        })
    }

    private fun hideLoading() {
        binding.spinRecentT10Matches.visibility = View.GONE
        binding.rvRecentT10.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.spinRecentT10Matches.visibility = View.VISIBLE
        binding.rvRecentT10.visibility = View.GONE
    }

}