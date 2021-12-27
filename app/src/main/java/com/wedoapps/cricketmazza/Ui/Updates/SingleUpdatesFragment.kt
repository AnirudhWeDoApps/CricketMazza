package com.wedoapps.cricketmazza.Ui.Updates

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.databinding.FragmentSingleUpdatesBinding

class SingleUpdatesFragment : Fragment(R.layout.fragment_single_updates) {

    private lateinit var binding: FragmentSingleUpdatesBinding
    private val args by navArgs<SingleUpdatesFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingleUpdatesBinding.bind(view)
        val data = args.news

        Glide.with(requireContext())
            .load(data.thumbnail)
            .into(binding.ivSingleUpdatesImage)

        binding.tvSingleUpdatesTitle.text = data.title
        binding.tvSingleUpdatesTime.text = data.dateAndTime
        binding.tvSingleUpdatesArticle.text = data.article

    }
}