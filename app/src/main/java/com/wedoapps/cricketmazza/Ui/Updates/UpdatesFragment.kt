package com.wedoapps.cricketmazza.Ui.Updates

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.Updates.Adapter.UpdatesRecyclerAdapter
import com.wedoapps.cricketmazza.Ui.Updates.Model.NewsModel
import com.wedoapps.cricketmazza.databinding.FragmentUpdatesBinding

class UpdatesFragment : Fragment(R.layout.fragment_updates), UpdatesRecyclerAdapter.OnUpdateClick {

    private lateinit var binding: FragmentUpdatesBinding
    private lateinit var updatesRecyclerAdapter: UpdatesRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdatesBinding.bind(view)

        updatesRecyclerAdapter = UpdatesRecyclerAdapter(this)
        topStoriesData()

        binding.rvUpdates.apply {
            setHasFixedSize(true)
            adapter = updatesRecyclerAdapter
        }
    }

    private fun topStoriesData() {
        val topStoriesDataList = mutableListOf(
            NewsModel(
                "1",
                R.drawable.ic_play_video,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            NewsModel(
                "1",
                R.drawable.ic_launcher_background,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            NewsModel(
                "1",
                R.drawable.ic_launcher_background,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            NewsModel(
                "1",
                R.drawable.ic_launcher_background,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            NewsModel(
                "1",
                R.drawable.ic_launcher_background,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
            NewsModel(
                "1",
                R.drawable.ic_launcher_background,
                "Pollard ruled out of Indian tour with hamstring injury",
                getString(R.string.loreum_ipsum),
                "24 Oct 2021 . 10:35 AM, Sun"
            ),
        )
        updatesRecyclerAdapter.differ.submitList(topStoriesDataList)
    }

    override fun onUpdateClick(newsModel: NewsModel) {
        val action = UpdatesFragmentDirections.actionUpdateToSingleUpdatesFragment(newsModel)
        findNavController().navigate(action)

    }
}