package com.gedehari.pubpix.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gedehari.pubpix.model.post.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll(): List<Post>

    @Insert
    fun insertAll(vararg posts: Post)

    @Delete
    fun delete(post: Post)
}