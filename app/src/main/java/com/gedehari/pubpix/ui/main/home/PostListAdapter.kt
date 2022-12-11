package com.gedehari.pubpix.ui.main.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gedehari.pubpix.databinding.ItemPostBinding
import com.gedehari.pubpix.model.post.Post

class PostListAdapter: ListAdapter<Post, PostListAdapter.PostViewHolder>(DiffCallback) {
    class PostViewHolder(private var binding: ItemPostBinding): ViewHolder(binding.root) {
        fun bind(post: Post) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return  oldItem.caption  == newItem.caption  ||
                        oldItem.authorId == newItem.authorId
            }

            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.postId == newItem.postId
            }
        }
    }
}