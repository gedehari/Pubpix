package com.gedehari.pubpix.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.gedehari.pubpix.Config
import com.gedehari.pubpix.database.AppDatabase
import com.gedehari.pubpix.model.post.Post
import com.gedehari.pubpix.network.ApiService
import com.haroldadmin.cnradapter.NetworkResponse

object PostRepository {
    suspend fun refreshPosts(allPosts: LiveData<List<Post>>): Boolean {
        Log.i("PubPix", allPosts.value.toString())

        when (val response = ApiService.getClient().getPosts()) {
            is NetworkResponse.Success -> {
                val posts = response.body
                if (posts.isEmpty())
                    return false
                posts.forEach {
                    AppDatabase.getInstance().postDao().insert(it.post)
                }

                return true
            }
            else -> {
                return false
            }
        }
    }

    fun getImageUrl(imageUuid: String): String {
        return "${Config.BASE_URL}uploads/${imageUuid}.jpg"
    }
}