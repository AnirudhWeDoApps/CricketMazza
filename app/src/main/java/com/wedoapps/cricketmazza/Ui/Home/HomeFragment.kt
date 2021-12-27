package com.wedoapps.cricketmazza.Ui.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.CricketMazzaViewModel
import com.wedoapps.cricketmazza.Ui.Home.Adapter.FeaturedVideosRecyclerAdpater
import com.wedoapps.cricketmazza.Ui.Home.Adapter.HomeTopStoriesRecyclerAdapter
import com.wedoapps.cricketmazza.Ui.Home.Adapter.RecentMatchWiseRecyclerAdapter
import com.wedoapps.cricketmazza.Ui.Home.Adapter.RecentMatchesRecyclerAdapter
import com.wedoapps.cricketmazza.Ui.Home.Models.FeaturedVideosData
import com.wedoapps.cricketmazza.Ui.Home.Models.RecentMatchData
import com.wedoapps.cricketmazza.Ui.Home.Models.RecentMatchWiseData
import com.wedoapps.cricketmazza.Ui.MainActivity
import com.wedoapps.cricketmazza.Ui.Updates.Model.NewsModel
import com.wedoapps.cricketmazza.Utils.Constants.TAG
import com.wedoapps.cricketmazza.Utils.Resource
import com.wedoapps.cricketmazza.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home), RecentMatchesRecyclerAdapter.OnClicks,
    HomeTopStoriesRecyclerAdapter.OnTopStoriesClick {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recentMatchAdapter: RecentMatchesRecyclerAdapter
    private lateinit var recentMatchWiseAdapter: RecentMatchWiseRecyclerAdapter
    private lateinit var featuredVideosRecyclerAdpater: FeaturedVideosRecyclerAdpater
    private lateinit var topStoriesRecyclerAdapter: HomeTopStoriesRecyclerAdapter
    private lateinit var viewModel: CricketMazzaViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel

        recentMatchAdapter = RecentMatchesRecyclerAdapter(this)
        recentMatchWiseAdapter = RecentMatchWiseRecyclerAdapter()
        featuredVideosRecyclerAdpater = FeaturedVideosRecyclerAdpater()
        topStoriesRecyclerAdapter = HomeTopStoriesRecyclerAdapter(this)
        recentMatchData()
        recentMatchWiseData()
        featuredVideoData()
        topStoriesData()

        viewModel.getMatch().observe(requireActivity(), {
            Log.d(TAG, "onViewCreated: ${it.data}")
        })

        binding.rvRecentMatches.apply {
            setHasFixedSize(true)
            adapter = recentMatchAdapter
        }

        binding.rvRecentMatchWise.apply {
            setHasFixedSize(true)
            adapter = recentMatchWiseAdapter
        }

        binding.rvHomeFeaturedVideo.apply {
            setHasFixedSize(true)
            adapter = featuredVideosRecyclerAdpater
        }

        binding.rvHomeTopStories.apply {
            setHasFixedSize(true)
            adapter = topStoriesRecyclerAdapter
        }

        binding.btnHomeViewMore.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToUpdate())
        }
        val snapHelperMatchWise = PagerSnapHelper()
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvRecentMatches)
        snapHelperMatchWise.attachToRecyclerView(binding.rvRecentMatchWise)
        binding.indRecent.attachToRecyclerView(binding.rvRecentMatches)
        binding.indRecentMatchwise.attachToRecyclerView(binding.rvRecentMatchWise)

        binding.rvRecentMatchWise.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
        }

    }

    private fun recentMatchData() {

        viewModel.getHomeRecentMatch().observe(requireActivity(), { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoading()
                    Log.d(TAG, "response Status: Success")
                    recentMatchAdapter.differ.submitList(response.data)
                }

                is Resource.Error -> {
                    showLoading()
                    Log.d(TAG, "response Status: ${response.message}")
                }

                is Resource.Loading -> {
                    showLoading()
                    Log.d(TAG, "response Status: LOADING")
                }
            }
        })
    }

    private fun recentMatchWiseData() {
        val recentMatchWiseDataList = mutableListOf(
            RecentMatchWiseData(
                "1",
                true,
                "Series Match",
                "Lanka Premier League, 2021",
                R.mipmap.ic_launcher,
                "GGD",
                "4-0",
                R.mipmap.ic_launcher,
                "JKS",
                "0-4",
                6,
                2,
                "05 Dec 2021",
                "23 Dec 2021"
            ),
            RecentMatchWiseData(
                "1",
                true,
                "Series Match",
                "Lanka Premier League, 2021",
                R.mipmap.ic_launcher,
                "GGD",
                "4-0",
                R.mipmap.ic_launcher,
                "JKS",
                "0-4",
                6,
                2,
                "05 Dec 2021",
                "23 Dec 2021"
            ),
            RecentMatchWiseData(
                "1",
                false,
                "Series Match",
                "Lanka Premier League, 2021",
                R.mipmap.ic_launcher,
                "GGD",
                "4-0",
                R.mipmap.ic_launcher,
                "JKS",
                "0-4",
                6,
                2,
                "05 Dec 2021",
                "23 Dec 2021"
            ),
            RecentMatchWiseData(
                "1",
                true,
                "Series Match",
                "Lanka Premier League, 2021",
                R.mipmap.ic_launcher,
                "GGD",
                "4-0",
                R.mipmap.ic_launcher,
                "JKS",
                "0-4",
                6,
                2,
                "05 Dec 2021",
                "23 Dec 2021"
            ),
            RecentMatchWiseData(
                "1",
                false,
                "Series Match",
                "Lanka Premier League, 2021",
                R.mipmap.ic_launcher,
                "GGD",
                "4-0",
                R.mipmap.ic_launcher,
                "JKS",
                "0-4",
                6,
                2,
                "05 Dec 2021",
                "23 Dec 2021"
            ),
            RecentMatchWiseData(
                "1",
                true,
                "Series Match",
                "Lanka Premier League, 2021",
                R.mipmap.ic_launcher,
                "GGD",
                "4-0",
                R.mipmap.ic_launcher,
                "JKS",
                "0-4",
                6,
                2,
                "05 Dec 2021",
                "23 Dec 2021"
            ),
            RecentMatchWiseData(
                "1",
                false,
                "Series Match",
                "Lanka Premier League, 2021",
                R.mipmap.ic_launcher,
                "GGD",
                "4-0",
                R.mipmap.ic_launcher,
                "JKS",
                "0-4",
                6,
                2,
                "05 Dec 2021",
                "23 Dec 2021"
            ),
        )
        recentMatchWiseAdapter.differ.submitList(recentMatchWiseDataList)
    }

    private fun featuredVideoData() {
        val featuredVideoDataList = mutableListOf(
            FeaturedVideosData(
                "1",
                R.mipmap.ic_launcher,
                "INDIA VS NEWZEALAND T20 World Cup 2021 Pre Match Analysis",
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            FeaturedVideosData(
                "1",
                R.mipmap.ic_launcher,
                "INDIA VS NEWZEALAND T20 World Cup 2021 Pre Match Analysis",
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            FeaturedVideosData(
                "1",
                R.mipmap.ic_launcher,
                "INDIA VS NEWZEALAND T20 World Cup 2021 Pre Match Analysis",
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            FeaturedVideosData(
                "1",
                R.mipmap.ic_launcher,
                "INDIA VS NEWZEALAND T20 World Cup 2021 Pre Match Analysis",
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            FeaturedVideosData(
                "1",
                R.mipmap.ic_launcher,
                "INDIA VS NEWZEALAND T20 World Cup 2021 Pre Match Analysis",
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
        )
        featuredVideosRecyclerAdpater.differ.submitList(featuredVideoDataList)
    }

    private fun topStoriesData() {
        val topStoriesDataList = mutableListOf(
            NewsModel(
                "1",
                R.drawable.ic_play_video,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum) + getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            NewsModel(
                "1",
                R.drawable.ic_launcher_background,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum) + getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            NewsModel(
                "1",
                R.drawable.ic_launcher_background,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum) + getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            NewsModel(
                "1",
                R.drawable.ic_launcher_background,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum) + getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            NewsModel(
                "1",
                R.drawable.ic_launcher_background,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum) + getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            NewsModel(
                "1",
                R.drawable.ic_launcher_background,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum) + getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
        )
        topStoriesRecyclerAdapter.differ.submitList(topStoriesDataList)
    }

    override fun onCardClick(recentMatch: RecentMatchData) {
        val action = HomeFragmentDirections.actionHomeToRecentMatchDetailFragment(recentMatch)
        findNavController().navigate(action)
    }

    override fun onPointsTableClick(recentMatch: RecentMatchData) {
        val action = HomeFragmentDirections.actionHomeToPointsTableFragment(recentMatch)
        findNavController().navigate(action)
    }

    override fun onScorecardClick(recentMatch: RecentMatchData) {
        val action = HomeFragmentDirections.actionHomeToScoreCardFragment(recentMatch)
        findNavController().navigate(action)
    }

    override fun onStoriesClicked(newsModel: NewsModel) {
        val action = HomeFragmentDirections.actionHomeToSingleUpdatesFragment(newsModel)
        findNavController().navigate(action)
    }

    private fun hideLoading() {
        binding.spinRecentMatches.visibility = View.GONE
        binding.rvRecentMatches.visibility = View.VISIBLE
        binding.indRecent.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.spinRecentMatches.visibility = View.VISIBLE
        binding.rvRecentMatches.visibility = View.INVISIBLE
        binding.indRecent.visibility = View.INVISIBLE
    }

}