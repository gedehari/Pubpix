package com.gedehari.pubpix.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.gedehari.pubpix.model.post.Post
import com.gedehari.pubpix.model.post.PostWithUser
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
//    @Transaction
//    @Query("SELECT * FROM post ORDER BY postId DESC")
//    fun getAll(): List<PostWithUser>

    @Query("SELECT * FROM post")
    fun getPosts(): Flow<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg posts: Post)

    @Delete
    suspend fun delete(post: Post)
}