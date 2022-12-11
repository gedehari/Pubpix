package com.gedehari.pubpix.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gedehari.pubpix.database.dao.PostDao
import com.gedehari.pubpix.model.post.PostWithUser
import com.gedehari.pubpix.repo.PostRepository

class HomeViewModel : ViewModel() {
    lateinit var allPosts: LiveData<List<PostWithUser>>

    suspend fun fetchLatestPosts() {

    }
}

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
