package com.wedoapps.cricketmazza.Ui.Upcoming

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.Upcoming.ViewPagerFragments.AllUpcomingMatchFragment
import com.wedoapps.cricketmazza.Ui.Upcoming.ViewPagerFragments.OdiUpcomingMatchFragment
import com.wedoapps.cricketmazza.Ui.Upcoming.ViewPagerFragments.T20UpcomingMatchFragment
import com.wedoapps.cricketmazza.Ui.Upcoming.ViewPagerFragments.TestUpcomingMatchFragment
import com.wedoapps.cricketmazza.Utils.ViewPagerAdapter
import com.wedoapps.cricketmazza.databinding.FragmentUpcomingsBinding

class UpcomingFragment : Fragment(R.layout.fragment_upcomings) {

    private lateinit var binding: FragmentUpcomingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpcomingsBinding.bind(view)
        val fragmentList = arrayListOf(
            AllUpcomingMatchFragment(),
            OdiUpcomingMatchFragment(),
            T20UpcomingMatchFragment(),
            TestUpcomingMatchFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            childFragmentManager,
            lifecycle
        )

        binding.upcomingViewPager.adapter = adapter

        TabLayoutMediator(binding.upcomingTabLayout, binding.upcomingViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.all)
                1 -> tab.text = getString(R.string.odi)
                2 -> tab.text = getString(R.string.T20)
                3 -> tab.text = getString(R.string.test)
            }
        }.attach()
    }

}