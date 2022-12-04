package com.gedehari.pubpix.model.post

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.gedehari.pubpix.model.user.User
import java.util.Date

@Entity(tableName = "post")
data class Post(
    @PrimaryKey
    val postId: Int,
    val caption: String,
    val imageUuid: String,
    val createdAt: Date,
    val author: User
    //val authorId: Int
)

//data class PostWithUser(
//    @Embedded val post: Post,
//    @Relation(
//        parentColumn = "postId",
//        entityColumn = "userId"
//    )
//    val user: User
//)