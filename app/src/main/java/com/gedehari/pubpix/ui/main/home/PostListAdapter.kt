package com.gedehari.pubpix.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.gedehari.pubpix.databinding.ItemPostBinding
import com.gedehari.pubpix.model.post.Post
import com.gedehari.pubpix.repo.PostRepository

class PostListAdapter: ListAdapter<Post, PostListAdapter.PostViewHolder>(DiffCallback) {
    class PostViewHolder(private var binding: ItemPostBinding): ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.apply {
                postCaption.text = post.caption
                postAuthor.text = post.authorId.toString()

                val img = PostRepository.getImageUrl(post.imageUuid).toUri().buildUpon().build()
                postImage.load(img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
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