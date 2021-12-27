package com.wedoapps.cricketmazza.Ui.Upcoming.Adapter

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
import com.wedoapps.cricketmazza.databinding.LayoutUpcomingTabBinding

class UpcomingRecyclerAdapter : RecyclerView.Adapter<UpcomingRecyclerAdapter.UpcomingViewHolder>() {

    inner class UpcomingViewHolder(private val binding: LayoutUpcomingTabBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val storageRef = FirebaseStorage.getInstance().reference
        private var firestore = FirebaseFirestore.getInstance()
        private val firestoreRef = firestore.collection("MatchList")

        fun bind(recentMatchData: RecentMatchData) {
            binding.apply {

                tvLiveUpcomingMatchType.text = "T-20"
                tvUpcomingSeriesName.text = recentMatchData.MatchDetail

                tvUpcomingTeam1Name.text = recentMatchData.Team1
                tvUpcomingTeam1FullName.text = recentMatchData.Team1
                storageRef.child("FlagIcon/" + recentMatchData.Team1?.trim { it <= ' ' } + ".png")
                    .downloadUrl.addOnSuccessListener { uri ->
                        Glide.with(itemView.context)
                            .load(uri)
                            .centerCrop()
                            .placeholder(R.drawable.imgpsh_fullsize_anim)
                            .into(ivUpcomingTeam1Flag)
                    }.addOnFailureListener {
                        ivUpcomingTeam1Flag.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.imgpsh_fullsize_anim
                            )
                        )
                    }

                tvUpcomingTeam2Name.text = recentMatchData.Team2
                tvUpcomingTeam2FullName.text = recentMatchData.Team2
                storageRef.child("FlagIcon/" + recentMatchData.Team2?.trim { it <= ' ' } + ".png")
                    .downloadUrl.addOnSuccessListener { uri ->

                        Glide.with(itemView.context)
                            .load(uri)
                            .centerCrop()
                            .placeholder(R.drawable.imgpsh_fullsize_anim)
                            .into(ivUpcomingTeam2Flag)
                    }.addOnFailureListener {
                        ivUpcomingTeam2Flag.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.imgpsh_fullsize_anim
                            )
                        )
                    }

                tvUpcomingMatchDataAndTime.text = recentMatchData.MatchDetail
                tvUpcomingTabVenue.text = recentMatchData.Venue

                tvUpcomingMatchNo.text = "2nd Match"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val binding =
            LayoutUpcomingTabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        if (currentItem != null) {
            holder.bind(currentItem)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<RecentMatchData>() {
        override fun areItemsTheSame(oldItem: RecentMatchData, newItem: RecentMatchData): Boolean {
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