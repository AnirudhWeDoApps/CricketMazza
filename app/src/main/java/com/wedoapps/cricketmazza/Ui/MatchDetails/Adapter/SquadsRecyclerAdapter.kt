package com.wedoapps.cricketmazza.Ui.MatchDetails.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Utils.Constants.PLAYER
import com.wedoapps.cricketmazza.databinding.LayoutSquadItemBinding

class SquadsRecyclerAdapter(private val data: List<String>) :
    RecyclerView.Adapter<SquadsRecyclerAdapter.SquadsViewHolder>() {

    inner class SquadsViewHolder(private val binding: LayoutSquadItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val storageRef = FirebaseStorage.getInstance().reference


        fun bind(name: String) {
            binding.apply {
                if (adapterPosition % 2 == 0) {
                    coSquad.background =
                        ContextCompat.getDrawable(itemView.context, R.color.transparent_black)
                } else {
                    coSquad.background =
                        ContextCompat.getDrawable(itemView.context, android.R.color.transparent)
                }
                tvPlayerName.text = name

                storageRef.child(PLAYER + name.trim { it <= ' ' } + ".png")
                    .downloadUrl.addOnSuccessListener { uri ->

                        Glide.with(itemView.context)
                            .load(uri)
                            .centerCrop()
                            .placeholder(R.drawable.ic_cricketer_place_holder)
                            .into(ivPlayer)
                    }.addOnFailureListener {
                        ivPlayer.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.ic_cricketer_place_holder
                            )
                        )
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadsViewHolder {
        val binding =
            LayoutSquadItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SquadsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SquadsViewHolder, position: Int) {
        val currentItem = data[position]

        if (currentItem.isNotEmpty()) {
            holder.bind(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}