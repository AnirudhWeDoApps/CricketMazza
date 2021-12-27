package com.wedoapps.cricketmazza.Ui.Home.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wedoapps.cricketmazza.Ui.Home.Models.RecentMatchWiseData
import com.wedoapps.cricketmazza.databinding.LayoutHomeRecentMatchWiseBinding

class RecentMatchWiseRecyclerAdapter :
    RecyclerView.Adapter<RecentMatchWiseRecyclerAdapter.RecentMatchWiseViewHolder>() {

    inner class RecentMatchWiseViewHolder(private val binding: LayoutHomeRecentMatchWiseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(matchWiseData: RecentMatchWiseData) {
            binding.apply {
                tvRecentMatchWiseSeriesType.text = matchWiseData.seriesType

                if (matchWiseData.isSeriesLive) {
                    coInnnerMatchwise.visibility = View.VISIBLE
                    innerMatchwiseMatchNotLive.visibility = View.INVISIBLE
                } else {
                    innerMatchwiseMatchNotLive.visibility = View.VISIBLE
                    coInnnerMatchwise.visibility = View.INVISIBLE
                }

                tvMatchwiseTeam1Name.text = matchWiseData.team1Name
                tvMatchwiseTeam1Score.text = matchWiseData.team1Score
                Glide.with(itemView)
                    .load(matchWiseData.team1Flag)
                    .into(ivMathcwiseTeam1Flag)

                tvMatchwiseTeam2Name.text = matchWiseData.team2Name
                tvMatchwiseTeam2Score.text = matchWiseData.team2Score
                Glide.with(itemView)
                    .load(matchWiseData.team2Flag)
                    .into(ivMathcwiseTeam2Flag)

                tvMatchwiseTotalMatches.text = "(${matchWiseData.totalMatches})"
                tvMatchwiseTotalLeft.text = "(${matchWiseData.totalLeft})"

                tvRecentMatchwiseSeriesName.text = matchWiseData.seriesName
                tvRecentMatchwiseStartEnd.text =
                    "${matchWiseData.startData} To ${matchWiseData.endDate}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMatchWiseViewHolder {
        val binding = LayoutHomeRecentMatchWiseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecentMatchWiseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentMatchWiseViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private val differCallback = object : DiffUtil.ItemCallback<RecentMatchWiseData>() {
        override fun areItemsTheSame(
            oldItem: RecentMatchWiseData,
            newItem: RecentMatchWiseData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecentMatchWiseData,
            newItem: RecentMatchWiseData
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}