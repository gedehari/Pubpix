package com.gedehari.pubpix.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.gedehari.pubpix.model.post.Post
import com.gedehari.pubpix.model.post.PostWithUser

@Dao
interface PostDao {
    @Transaction
    @Query("SELECT * FROM post")
    fun getAll(): LiveData<List<PostWithUser>>

    @Insert
    fun insert(vararg posts: Post)

    @Delete
    fun delete(post: Post)
}