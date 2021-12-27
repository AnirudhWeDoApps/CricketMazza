package com.wedoapps.cricketmazza.Ui.Recents.Adapter

import android.annotation.SuppressLint
import android.util.Log
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
import com.wedoapps.cricketmazza.Utils.Constants
import com.wedoapps.cricketmazza.databinding.LayoutRecentBinding

class RecentRecyclerAdapter : RecyclerView.Adapter<RecentRecyclerAdapter.RecentViewHolder>() {

    inner class RecentViewHolder(private val binding: LayoutRecentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val storageRef = FirebaseStorage.getInstance().reference
        private var firestore = FirebaseFirestore.getInstance()
        private val firestoreRef = firestore.collection("MatchList")

        @SuppressLint("SetTextI18n")
        fun bind(recentData: RecentMatchData) {
            binding.apply {
                tvRecentSeriesName.text = recentData.Series
                tvRecentMatchAndLocation.text = recentData.NoOfMatch + recentData.Venue

                tvRecentTeam1Name.text = recentData.Team1

                storageRef.child("FlagIcon/" + recentData.Team1?.trim { it <= ' ' } + ".png")
                    .downloadUrl.addOnSuccessListener { uri ->

                        Glide.with(itemView.context)
                            .load(uri)
                            .centerCrop()
                            .placeholder(R.drawable.imgpsh_fullsize_anim)
                            .into(ivRecentTeam1Flag)
                    }.addOnFailureListener {
                        ivRecentTeam1Flag.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.imgpsh_fullsize_anim
                            )
                        )
                    }

                firestoreRef.document(recentData.id!!).collection("LiveMatch")
                    .document("ScoreTeam1")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.w(Constants.TAG, "Listen Failed", error)
                            return@addSnapshotListener
                        }


                        if (value != null) {
                            tvRecentTeam1runAndOver.text =
                                "${value.get(" Score ").toString()}  (${value.get("Over")})"

                            Log.d(Constants.TAG, "team1: ${value.data}")
                        } else {
                            Log.d(Constants.TAG, "No Data")
                        }
                    }

                tvRecentTeam2Name.text = recentData.Team2
                storageRef.child("FlagIcon/" + recentData.Team2?.trim { it <= ' ' } + ".png")
                    .downloadUrl.addOnSuccessListener { uri ->

                        Glide.with(itemView.context)
                            .load(uri)
                            .centerCrop()
                            .placeholder(R.drawable.imgpsh_fullsize_anim)
                            .into(ivRecentTeam2Flag)
                    }.addOnFailureListener {
                        ivRecentTeam2Flag.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.imgpsh_fullsize_anim
                            )
                        )
                    }

                firestoreRef.document(recentData.id!!).collection("LiveMatch")
                    .document("ScoreTeam2")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.w(Constants.TAG, "Listen Failed", error)
                            return@addSnapshotListener
                        }


                        if (value != null) {
                            tvRecentTeam2runAndOver.text =
                                "${value.get(" Score ").toString()}  (${value.get("Over")})"

                            Log.d(Constants.TAG, "team1: ${value.data}")
                        } else {
                            Log.d(Constants.TAG, "No Data")
                        }
                    }

                tvRecentMatchDataAndTime.text = recentData.MatchDate.toString()
                tvRecentTabMatchStatus.text = recentData.MatchStatus
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val binding =
            LayoutRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
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