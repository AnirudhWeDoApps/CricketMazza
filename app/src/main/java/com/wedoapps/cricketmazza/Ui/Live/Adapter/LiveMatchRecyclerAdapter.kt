package com.wedoapps.cricketmazza.Ui.Live.Adapter

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
import com.wedoapps.cricketmazza.Utils.Constants.TAG
import com.wedoapps.cricketmazza.databinding.LayoutLiveMatchBinding

class LiveMatchRecyclerAdapter :
    RecyclerView.Adapter<LiveMatchRecyclerAdapter.LiveMatchViewHolder>() {

    inner class LiveMatchViewHolder(private val binding: LayoutLiveMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val storageRef = FirebaseStorage.getInstance().reference
        private var firestore = FirebaseFirestore.getInstance()
        private val firestoreRef = firestore.collection("MatchList")

        @SuppressLint("SetTextI18n")
        fun bind(liveMatch: RecentMatchData) {
            binding.apply {
                if (liveMatch.Series.isNullOrEmpty()) {
                    tvLivematchSeriesName.text = liveMatch.MatchDetail
                } else {
                    tvLivematchSeriesName.text = liveMatch.Series
                }

                tvLivematchTeam1Name.text = liveMatch.Team1

                firestoreRef.document(liveMatch.id!!).collection("LiveMatch")
                    .document("ScoreTeam1")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.w(TAG, "Listen Failed", error)
                            return@addSnapshotListener
                        }


                        if (value != null) {
                            tvLivematchTeam1Score.text =
                                value.get("Score").toString()
                            tvLivematchTeam1Over.text = "${value.get("Over")} Overs"

                            Log.d(TAG, "team1: ${value.data}")
                        } else {
                            Log.d(TAG, "No Data")
                        }
                    }



                storageRef.child("FlagIcon/" + liveMatch.Team1?.trim { it <= ' ' } + ".png")
                    .downloadUrl.addOnSuccessListener { uri ->

                        Glide.with(itemView.context)
                            .load(uri)
                            .centerCrop()
                            .placeholder(R.drawable.imgpsh_fullsize_anim)
                            .into(ivLivematchTeam1Flag)
                    }.addOnFailureListener {
                        ivLivematchTeam1Flag.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.imgpsh_fullsize_anim
                            )
                        )
                    }

                tvLivematchTeam2Name.text = liveMatch.Team2

                firestoreRef.document(liveMatch.id!!).collection("LiveMatch")
                    .document("ScoreTeam1")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.w(TAG, "Listen Failed", error)
                            return@addSnapshotListener
                        }


                        if (value != null) {
                            tvLivematchTeam2Score.text =
                                value.get("Score").toString()
                            tvLivematchTeam2Over.text = "${value.get("Over")} Overs"

                            Log.d(TAG, "team1: ${value.data}")
                        } else {
                            Log.d(TAG, "No Data")
                        }
                    }

                storageRef.child("FlagIcon/" + liveMatch.Team2?.trim { it <= ' ' } + ".png")
                    .downloadUrl.addOnSuccessListener { uri ->

                        Glide.with(itemView.context)
                            .load(uri)
                            .centerCrop()
                            .placeholder(R.drawable.imgpsh_fullsize_anim)
                            .into(ivLivematchTeam2Flag)
                    }.addOnFailureListener {
                        ivLivematchTeam2Flag.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.imgpsh_fullsize_anim
                            )
                        )
                    }

                firestoreRef.document(liveMatch.id!!).collection("MatchRate").document("Match")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.w(TAG, "Listen Failed", error)
                            return@addSnapshotListener
                        }

                        if (value != null) {
                            binding.apply {
                                Log.d(TAG, "bind: ${value.data}")
                                tvLivematchTeam1Fav.text = value.get("Rate1").toString()
                                tvLivematchTeam2Fav.text = value.get("Rate2").toString()
                                tvLivematchFavTeam.text = value.get("FavTeam").toString()
                            }
                        }
                    }

                firestoreRef.document(liveMatch.id!!).collection("LiveMatch")
                    .document("RunRateInfo")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.w(TAG, "Listen Failed", error)
                            return@addSnapshotListener
                        }


                        if (value != null) {
                            tvLivematchCrr.text =
                                value.get("CRR").toString()
                            tvLivematchRrr.text = value.get("RRR").toString()
                            tvLivematchStatus.text = value.get("OtherInfo").toString()
                            Log.d(TAG, "team1: ${value.data}")
                        } else {
                            Log.d(TAG, "No Data")
                        }
                    }

                /*     tvLivematchCrr.text = liveMatch.crr
                     tvLivematchFavTeam.text = liveMatch.favTeamName
                     tvLivematchRrr.text = liveMatch.rrr

                     tvLivematchStatus.text = liveMatch.matchStatus*/


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveMatchViewHolder {
        val binding =
            LayoutLiveMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LiveMatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LiveMatchViewHolder, position: Int) {
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