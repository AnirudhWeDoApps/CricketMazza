package com.wedoapps.cricketmazza.Ui.MatchDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.CricketMazzaViewModel
import com.wedoapps.cricketmazza.Ui.Home.Models.RecentMatchData
import com.wedoapps.cricketmazza.Ui.MainActivity
import com.wedoapps.cricketmazza.Ui.MatchDetails.Adapter.SquadsRecyclerAdapter
import com.wedoapps.cricketmazza.Utils.Constants.INFO_DATA
import com.wedoapps.cricketmazza.Utils.Constants.TAG
import com.wedoapps.cricketmazza.Utils.Resource
import com.wedoapps.cricketmazza.Utils.setBgSelected
import com.wedoapps.cricketmazza.databinding.FragmentSquadsBinding

class SquadsFragment : Fragment(R.layout.fragment_squads) {

    private lateinit var binding: FragmentSquadsBinding
    private lateinit var data: RecentMatchData
    private lateinit var viewModel: CricketMazzaViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSquadsBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        data = arguments?.getParcelable(INFO_DATA)!!

        binding.tvSquadsTeam1.text = data.Team1
        binding.tvSquadsTeam2.text = data.Team2

        var team1 = listOf<String>()
        var team2 = listOf<String>()

        viewModel.getInfo(data.id!!).observe(requireActivity(), { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoading()
                    team1 = response.data?.TeamSquade?.Team1?.split(",")!!.map { playerName ->
                        playerName.trim()
                    }
                    team2 = response.data.TeamSquade?.Team2?.split(",")!!.map { playerName ->
                        playerName.trim()
                    }

                    val squadsRecyclerAdapter = SquadsRecyclerAdapter(team1)

                    binding.rvSquad.apply {
                        setHasFixedSize(true)
                        isNestedScrollingEnabled = false
                        adapter = squadsRecyclerAdapter
                    }

                }

                is Resource.Error -> {
                    Log.d(TAG, "getSquadTabError: ${response.message}")
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }
        })

        binding.tvSquadsTeam1.setOnClickListener {
            binding.tvSquadsTeam1.setBgSelected(requireContext(), binding.tvSquadsTeam2)
            val squadsRecyclerAdapter = SquadsRecyclerAdapter(team1)

            binding.rvSquad.apply {
                setHasFixedSize(true)
                isNestedScrollingEnabled = false
                adapter = squadsRecyclerAdapter
            }

        }

        binding.tvSquadsTeam2.setOnClickListener {
            binding.tvSquadsTeam2.setBgSelected(requireContext(), binding.tvSquadsTeam1)
            val squadsRecyclerAdapter = SquadsRecyclerAdapter(team2)

            binding.rvSquad.apply {
                setHasFixedSize(true)
                isNestedScrollingEnabled = false
                adapter = squadsRecyclerAdapter
                adapter = squadsRecyclerAdapter
            }
        }
    }

    fun newInstance(data: RecentMatchData?): SquadsFragment {
        val fragment = SquadsFragment()
        val args = Bundle()
        args.putParcelable(INFO_DATA, data)
        fragment.arguments = args
        return fragment
    }

    private fun hideLoading() {
        binding.apply {
            spinSquads.visibility = View.GONE
            rvSquad.visibility = View.VISIBLE
        }
    }

    private fun showLoading() {
        binding.apply {
            rvSquad.visibility = View.GONE
            spinSquads.visibility = View.VISIBLE
        }
    }
}