package com.wedoapps.cricketmazza.Ui.Home.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wedoapps.cricketmazza.Ui.Home.Models.FeaturedVideosData
import com.wedoapps.cricketmazza.databinding.LayoutFeaturedVideosBinding

class FeaturedVideosRecyclerAdpater :
    RecyclerView.Adapter<FeaturedVideosRecyclerAdpater.FeaturedVideosViewHolder>() {

    inner class FeaturedVideosViewHolder(private val binding: LayoutFeaturedVideosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(videoData: FeaturedVideosData) {
            binding.apply {
                Glide.with(itemView)
                    .load(videoData.thumbnail)
                    .into(ivHomeThumbnail)

                tvHomeVideoTitle.text = videoData.title
                tvHomeVideoTime.text = videoData.dataAndTime
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedVideosViewHolder {
        val binding = LayoutFeaturedVideosBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FeaturedVideosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeaturedVideosViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<FeaturedVideosData>() {
        override fun areItemsTheSame(
            oldItem: FeaturedVideosData,
            newItem: FeaturedVideosData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FeaturedVideosData,
            newItem: FeaturedVideosData
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}