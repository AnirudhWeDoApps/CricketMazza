package com.wedoapps.cricketmazza.Ui.Home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.MatchDetails.*
import com.wedoapps.cricketmazza.Utils.ViewPagerAdapter
import com.wedoapps.cricketmazza.databinding.FragmentRecentMatchDetailBinding

class RecentMatchDetailFragment : Fragment(R.layout.fragment_recent_match_detail) {

    private lateinit var binding: FragmentRecentMatchDetailBinding
    private val args: RecentMatchDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecentMatchDetailBinding.bind(view)

        val fragments = arrayListOf(
            InfoFragment().newInstance(args.data),
            SquadsFragment().newInstance(args.data),
            PointsTableFragment().newInstance(args.data),
            BallByBallFragment().newInstance(args.data),
            ScoreCardFragment().newInstance(args.data),
            PlayerMatchFragment().newInstance(args.data)
        )

        val adapter = ViewPagerAdapter(
            fragments,
            childFragmentManager,
            lifecycle
        )

        binding.recentViewPager.adapter = adapter

        TabLayoutMediator(binding.recentTabLayout, binding.recentViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.info)
                1 -> tab.text = getString(R.string.squads)
                2 -> tab.text = getString(R.string.points_table)
                3 -> tab.text = getString(R.string.ball_by_ball)
                4 -> tab.text = getString(R.string.score_card)
                5 -> tab.text = getString(R.string.potm)
            }
        }.attach()
    }
}