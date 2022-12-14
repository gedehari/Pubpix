package com.gedehari.pubpix.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.gedehari.pubpix.database.AppDatabase
import com.gedehari.pubpix.model.post.Post
import com.gedehari.pubpix.repo.PostRepository

class HomeViewModel : ViewModel() {
    val allPosts = AppDatabase.getInstance().postDao().getPosts().asLiveData()

    suspend fun refreshPosts(): Boolean {
        return PostRepository.refreshPosts(allPosts)
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
