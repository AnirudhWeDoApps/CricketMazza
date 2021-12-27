package com.wedoapps.cricketmazza.Ui.MatchDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.Home.Models.RecentMatchData
import com.wedoapps.cricketmazza.Utils.Constants.INFO_DATA
import com.wedoapps.cricketmazza.databinding.FragmentBallByBallBinding

class BallByBallFragment : Fragment(R.layout.fragment_ball_by_ball) {

    private lateinit var binding: FragmentBallByBallBinding
    private lateinit var data: RecentMatchData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBallByBallBinding.bind(view)

        data = arguments?.getParcelable(INFO_DATA)!!
    }

    fun newInstance(data: RecentMatchData?): BallByBallFragment {
        val fragment = BallByBallFragment()
        val args = Bundle()
        args.putParcelable(INFO_DATA, data)
        fragment.arguments = args
        return fragment
    }

}