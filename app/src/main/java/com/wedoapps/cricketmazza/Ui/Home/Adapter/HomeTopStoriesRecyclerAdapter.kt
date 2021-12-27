package com.wedoapps.cricketmazza.Ui.Home.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wedoapps.cricketmazza.Ui.Updates.Model.NewsModel
import com.wedoapps.cricketmazza.databinding.LayoutHomeTopStoriesBigBinding
import com.wedoapps.cricketmazza.databinding.LayoutHomeTopStoriesSmallBinding

class HomeTopStoriesRecyclerAdapter(private val listener: OnTopStoriesClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BigTopStoryViewHolder(private val binding: LayoutHomeTopStoriesBigBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsModel: NewsModel) {
            binding.apply {
                tvTopStoriesArticle.visibility = View.GONE
                Glide.with(itemView)
                    .load(newsModel.thumbnail)
                    .into(ivStoriesMainThumbnail)

                tvTopStoriesTitle.text = newsModel.title
                tvTopStoriesTime.text = newsModel.dateAndTime

                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = differ.currentList[position]
                        if (item != null) {
                            listener.onStoriesClicked(item)
                        }
                    }
                }
            }
        }
    }

    inner class SmallTopStoryViewHolder(private val binding: LayoutHomeTopStoriesSmallBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsModel: NewsModel) {
            binding.apply {
                Glide.with(itemView)
                    .load(newsModel.thumbnail)
                    .into(ivTopStoriesSmallThumbanil)

                tvTopStoriesTitleSmall.text = newsModel.title
                tvTopStoriesTimeSmall.text = newsModel.dateAndTime

                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = differ.currentList[position]
                        if (item != null) {
                            listener.onStoriesClicked(item)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_MAIN) {
            val binding = LayoutHomeTopStoriesBigBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BigTopStoryViewHolder(binding)
        }
        val binding = LayoutHomeTopStoriesSmallBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SmallTopStoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentItem = differ.currentList[position]

        if (position == 0 && holder.itemViewType == TYPE_MAIN) {
            val mainView = holder as BigTopStoryViewHolder
            mainView.bind(differ.currentList[0])
        } else {
            val normal = holder as SmallTopStoryViewHolder
            normal.bind(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                TYPE_MAIN
            }
            else -> {
                TYPE_NORMAL
            }
        }
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

    companion object {
        private const val TYPE_MAIN = 0
        private const val TYPE_NORMAL = 1
    }

    interface OnTopStoriesClick {
        fun onStoriesClicked(newsModel: NewsModel)
    }
}