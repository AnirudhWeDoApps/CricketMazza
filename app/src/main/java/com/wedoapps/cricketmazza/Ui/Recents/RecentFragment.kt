package com.wedoapps.cricketmazza.Ui.Recents

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.Recents.ViewPagerFragments.OdiFragment
import com.wedoapps.cricketmazza.Ui.Recents.ViewPagerFragments.T10Fragment
import com.wedoapps.cricketmazza.Ui.Recents.ViewPagerFragments.T20Fragment
import com.wedoapps.cricketmazza.Ui.Recents.ViewPagerFragments.TestFragment
import com.wedoapps.cricketmazza.Utils.ViewPagerAdapter
import com.wedoapps.cricketmazza.databinding.FragmentRecentBinding

class RecentFragment : Fragment(R.layout.fragment_recent) {

    private lateinit var binding: FragmentRecentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecentBinding.bind(view)

        val fragmentList = arrayListOf(
            OdiFragment(),
            T20Fragment(),
            TestFragment(),
            T10Fragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            childFragmentManager,
            lifecycle
        )

        binding.recentViewPager.adapter = adapter

        TabLayoutMediator(binding.recentTabLayout, binding.recentViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.odi)
                1 -> tab.text = getString(R.string.T20)
                2 -> tab.text = getString(R.string.test)
                3 -> tab.text = getString(R.string.T10)
            }
        }.attach()
    }
}