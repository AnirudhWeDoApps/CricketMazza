package com.wedoapps.cricketmazza.Ui.MatchDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.CricketMazzaViewModel
import com.wedoapps.cricketmazza.Ui.Home.Models.RecentMatchData
import com.wedoapps.cricketmazza.Ui.MainActivity
import com.wedoapps.cricketmazza.Ui.MatchDetails.Model.Info
import com.wedoapps.cricketmazza.Utils.Constants.INFO_DATA
import com.wedoapps.cricketmazza.Utils.Constants.TAG
import com.wedoapps.cricketmazza.Utils.Resource
import com.wedoapps.cricketmazza.databinding.FragmentInfoBinding

class InfoFragment : Fragment(R.layout.fragment_info) {

    private lateinit var binding: FragmentInfoBinding
    private lateinit var data: RecentMatchData
    private lateinit var viewModel: CricketMazzaViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        data = arguments?.getParcelable(INFO_DATA)!!

        viewModel.getInfo(data.id!!).observe(requireActivity(), { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoading()
                    setData(response.data)
                }

                is Resource.Error -> {
                    Log.d(TAG, "getInfoTabError: ${response.message}")
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }
        })
    }

    private fun hideLoading() {
        binding.apply {
            spinInfo.visibility = View.GONE
            llInfo.visibility = View.VISIBLE
        }
    }

    private fun showLoading() {
        binding.apply {
            llInfo.visibility = View.GONE
            spinInfo.visibility = View.VISIBLE
        }
    }

    private fun setData(info: Info?) {
        binding.apply {
            tvInfoMatch.text = info?.NoOfMatch
            tvInfoMatchType.text = info?.MatchDetail
            tvInfoDate.text = info?.CurrentDate
            tvInfoSeries.text = info?.Series
            tvInfoVenue.text = info?.Venue
            tvInfoToss.text = info?.TossInfo
            tvInfoStatus.text = info?.MatchStatus
            tvInfoUmpire.text = info?.Umpire
            tvInfoThirdEmpire.text = info?.ThirdUmpire
            tvInfoReferee.text = info?.Referee
        }
    }

    fun newInstance(data: RecentMatchData?): InfoFragment {
        val fragment = InfoFragment()
        val args = Bundle()
        args.putParcelable(INFO_DATA, data)
        fragment.arguments = args
        return fragment
    }

}