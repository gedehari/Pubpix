package com.gedehari.pubpix.database

import com.gedehari.pubpix.model.post.Post
import com.gedehari.pubpix.network.ApiService

class PostRepository(private val apiService: ApiService) {
    suspend fun getPosts(from: Int = 0, limit: Int): List<Post> {
        return listOf() // none now lol
    }
}