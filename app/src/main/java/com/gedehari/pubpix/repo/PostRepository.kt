package com.gedehari.pubpix.repo

import androidx.lifecycle.LiveData
import com.gedehari.pubpix.model.post.PostWithUser

object PostRepository {
    suspend fun getPosts(from: Int = 0, limit: Int): LiveData<List<PostWithUser>> {
        TODO()
    }
}