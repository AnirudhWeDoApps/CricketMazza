package com.wedoapps.cricketmazza.Ui.Updates.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wedoapps.cricketmazza.Ui.Updates.Model.NewsModel
import com.wedoapps.cricketmazza.databinding.LayoutHomeTopStoriesBigBinding

class UpdatesRecyclerAdapter(private val listener: OnUpdateClick) :
    RecyclerView.Adapter<UpdatesRecyclerAdapter.UpdatesViewHolder>() {

    inner class UpdatesViewHolder(private val binding: LayoutHomeTopStoriesBigBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsModel: NewsModel) {
            binding.apply {
                Glide.with(itemView)
                    .load(newsModel.thumbnail)
                    .into(ivStoriesMainThumbnail)

                tvTopStoriesTitle.text = newsModel.title
                tvTopStoriesTime.text = newsModel.dateAndTime
                tvTopStoriesArticle.text = newsModel.article

                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = differ.currentList[position]
                        if (item != null) {
                            listener.onUpdateClick(item)
                        }
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdatesViewHolder {
        val binding = LayoutHomeTopStoriesBigBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UpdatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpdatesViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<NewsModel>() {
        override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    interface OnUpdateClick {
        fun onUpdateClick(newsModel: NewsModel)
    }

}