package com.wedoapps.cricketmazza.Ui.Home.Adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
import com.wedoapps.cricketmazza.Utils.Constants.TAG
import com.wedoapps.cricketmazza.databinding.LayoutHomeRecentMatchBinding

class RecentMatchesRecyclerAdapter(private val listener: OnClicks) :
    RecyclerView.Adapter<RecentMatchesRecyclerAdapter.RecentMatchesViewHolder>() {

    inner class RecentMatchesViewHolder(private val binding: LayoutHomeRecentMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val storageRef = FirebaseStorage.getInstance().reference
        private var firestore = FirebaseFirestore.getInstance()
        private val firestoreRef = firestore.collection("MatchList")

        @SuppressLint("SetTextI18n")
        fun bind(recentMatch: RecentMatchData) {
            binding.apply {
                tvRecentMatchNo.text = "${recentMatch.NoOfMatch} -"
                tvRecentSeries.text = recentMatch.Series
                tvRecentLocation.text = recentMatch.Venue


                tvRecentTeam1Name.text = recentMatch.Team1
                firestoreRef.document(recentMatch.id!!).collection("LiveMatch")
                    .document("ScoreTeam1")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.w(TAG, "Listen Failed", error)
                            return@addSnapshotListener
                        }

                        if (value != null) {
                            binding.apply {
                                tvRecentTeam1Score.text =
                                    "${value.get("Score")} (${value.get("Over")})"
                            }
                            Log.d(TAG, "team1: ${value.data}")
                        } else {
                            Log.d(TAG, "No Data")
                        }
                    }

                storageRef.child("FlagIcon/" + recentMatch.Team1?.trim { it <= ' ' } + ".png")
                    .downloadUrl.addOnSuccessListener { uri ->

                        Glide.with(itemView.context)
                            .load(uri)
                            .centerCrop()
                            .placeholder(R.drawable.imgpsh_fullsize_anim)
                            .into(ivRecentTeam1)
                    }.addOnFailureListener {
                        ivRecentTeam1.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.imgpsh_fullsize_anim
                            )
                        )
                    }

                tvRecentTeam2Name.text = recentMatch.Team2
                firestoreRef.document(recentMatch.id!!).collection("LiveMatch")
                    .document("ScoreTeam2")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.w(TAG, "Listen Failed", error)
                            return@addSnapshotListener
                        }

                        if (value != null) {
                            binding.apply {
                                tvRecentTeam2Score.text =
                                    "${value.get("Score")}  (${value.get("Over")})"
                            }
                            Log.d(TAG, "team2: ${value.data}")
                        } else {
                            Log.d(TAG, "No Data")
                        }
                    }


                storageRef.child("FlagIcon/" + recentMatch.Team2?.trim { it <= ' ' } + ".png")
                    .downloadUrl.addOnSuccessListener { uri ->

                        Glide.with(itemView.context)
                            .load(uri)
                            .centerCrop()
                            .placeholder(R.drawable.imgpsh_fullsize_anim)
                            .into(ivRecentTeam2)
                    }.addOnFailureListener {
                        ivRecentTeam2.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.imgpsh_fullsize_anim
                            )
                        )
                    }


                Glide.with(itemView.context)
                    .load(R.drawable.ic_play_video)
                    .centerCrop()
                    .into(ivRecentPotm)

//                tvRecentPotmName.text = recentMatch.potmName
                if (recentMatch.MatchStatus.equals("LIVE")) {
                    tvRecentMatchStatus.text = "In Progress..."
                    tvRecentPotm.visibility = View.GONE
                    ivRecentPotm.visibility = View.GONE
                    tvRecentPotmName.visibility = View.GONE
                } else if (recentMatch.MatchStatus.equals("UPCOMING")) {
                    tvRecentPotm.visibility = View.GONE
                    ivRecentPotm.visibility = View.GONE
                    tvRecentPotmName.visibility = View.GONE
                } else {
                    tvRecentMatchStatus.text = recentMatch.MatchResult
                    tvRecentPotm.visibility = View.VISIBLE
                    ivRecentPotm.visibility = View.VISIBLE
                    tvRecentPotmName.visibility = View.VISIBLE
                }

                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = differ.currentList[position]
                        if (item != null) {
                            listener.onCardClick(item)
                        }
                    }
                }

                tvRecentScorecard.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = differ.currentList[position]
                        if (item != null) {
                            listener.onScorecardClick(item)
                        }
                    }
                }

                tvRecentPointsTable.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = differ.currentList[position]
                        if (item != null) {
                            listener.onPointsTableClick(item)
                        }
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMatchesViewHolder {
        val binding = LayoutHomeRecentMatchBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecentMatchesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentMatchesViewHolder, position: Int) {
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

    interface OnClicks {
        fun onCardClick(recentMatch: RecentMatchData)
        fun onPointsTableClick(recentMatch: RecentMatchData)
        fun onScorecardClick(recentMatch: RecentMatchData)
    }

}