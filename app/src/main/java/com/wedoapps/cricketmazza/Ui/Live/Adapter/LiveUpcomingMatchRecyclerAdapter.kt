package com.wedoapps.cricketmazza.Ui.Live.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Ui.Home.Models.RecentMatchData
import com.wedoapps.cricketmazza.databinding.LayoutLiveUpcomingMatchesBinding

class LiveUpcomingMatchRecyclerAdapter :
    RecyclerView.Adapter<LiveUpcomingMatchRecyclerAdapter.LiveUpcomingMatchViewHolder>() {

    inner class LiveUpcomingMatchViewHolder(private val binding: LayoutLiveUpcomingMatchesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val storageRef = FirebaseStorage.getInstance().reference
        private var firestore = FirebaseFirestore.getInstance()
        private val firestoreRef = firestore.collection("MatchList")

        @SuppressLint("SetTextI18n")
        fun bind(upcomingData: RecentMatchData) {
            binding.apply {
                tvLiveUpcomingSeriesName.text = upcomingData.MatchDetail
                tvLiveUpcomingTimeDate.text = upcomingData.MatchDetail
                tvLiveUpcomingMatchType.text = "T20"

                tvLiveUpcomingTeam1Name.text = upcomingData.Team1

                storageRef.child("FlagIcon/" + upcomingData.Team1?.trim { it <= ' ' } + ".png")
                    .downloadUrl.addOnSuccessListener { uri ->

                        Glide.with(itemView.context)
                            .load(uri)
                            .centerCrop()
                            .placeholder(R.drawable.imgpsh_fullsize_anim)
                            .into(ivLiveUpcomingTeam1Flag)
                    }.addOnFailureListener {
                        ivLiveUpcomingTeam1Flag.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.imgpsh_fullsize_anim
                            )
                        )
                    }

                tvLiveUpcomingMarketTeam1.text = "26-28"
                tvLiveUpcomingOversTeam1Title.text = "20 Overs:"
                tvLiveUpcomingOversTeam1.text = "182-184"

                tvLiveUpcomingTeam2Name.text = upcomingData.Team2

                storageRef.child("FlagIcon/" + upcomingData.Team2?.trim { it <= ' ' } + ".png")
                    .downloadUrl.addOnSuccessListener { uri ->

                        Glide.with(itemView.context)
                            .load(uri)
                            .centerCrop()
                            .placeholder(R.drawable.imgpsh_fullsize_anim)
                            .into(ivLiveUpcomingTeam2Flag)
                    }.addOnFailureListener {
                        ivLiveUpcomingTeam2Flag.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.imgpsh_fullsize_anim
                            )
                        )
                    }

                tvLiveUpcomingMarketTeam2.text = "00-00"
                tvLiveUpcomingOversTeam2Title.text = "20 Overs:"
                tvLiveUpcomingOversTeam2.text = "15-155"

                tvLiveUpcomingTeam1ShortName.text = "DG"
                progressLiveUpcomingIndicatorTeam1.setAdProgress(90)

                tvLiveUpcomingTeam2ShortName.text = "WI"
                progressLiveUpcomingIndicatorTeam2.setAdProgress(90)

                tvLiveUpcomimgResultPercentage.text = "0%"
                tvLiveUpcomingPercentageResult.text = "TIE"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveUpcomingMatchViewHolder {
        val binding = LayoutLiveUpcomingMatchesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LiveUpcomingMatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LiveUpcomingMatchViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private val differCallback = object : DiffUtil.ItemCallback<RecentMatchData>() {
        override fun areItemsTheSame(
            oldItem: RecentMatchData,
            newItem: RecentMatchData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecentMatchData,
            newItem: RecentMatchData
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}