package com.gedehari.pubpix.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val userId: Int,
    val username: String,
    val displayName: String,
    val createdAt: Date
)