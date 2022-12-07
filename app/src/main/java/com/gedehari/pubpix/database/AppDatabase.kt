package com.gedehari.pubpix.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gedehari.pubpix.database.dao.PostDao
import com.gedehari.pubpix.model.post.Post
import com.gedehari.pubpix.model.user.User

@Database(entities = [Post::class, User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}