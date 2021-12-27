package com.wedoapps.cricketmazza.Ui.MatchDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.Home.Models.RecentMatchData
import com.wedoapps.cricketmazza.Utils.Constants.INFO_DATA
import com.wedoapps.cricketmazza.databinding.FragmentPointsTableBinding

class PointsTableFragment : Fragment(R.layout.fragment_points_table) {

    private lateinit var binding: FragmentPointsTableBinding
    private var data: RecentMatchData? = null
    private val args: PointsTableFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPointsTableBinding.bind(view)

        data = arguments?.getParcelable(INFO_DATA)
    }

    fun newInstance(data: RecentMatchData?): PointsTableFragment {
        val fragment = PointsTableFragment()
        val args = Bundle()
        args.putParcelable(INFO_DATA, data)
        fragment.arguments = args
        return fragment
    }

}