package com.gedehari.pubpix.network

import com.gedehari.pubpix.model.post.Post
import com.gedehari.pubpix.model.post.PostJson
import com.gedehari.pubpix.model.post.PostWithUser
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant
import java.util.Date

class PostJsonAdapter {
    @FromJson
    fun postFromJson(postJson: PostJson): PostWithUser {
        return PostWithUser(
            Post(
                postJson.id,
                postJson.caption,
                postJson.imageUuid,
                Date.from(Instant.parse(postJson.createdAt)),
                postJson.author.id
            ),
            UserJsonAdapter().userFromJson(postJson.author)
        )
    }

    @ToJson
    fun postToJson(post: PostWithUser): PostJson {
        return PostJson(
            post.post.postId,
            post.post.caption,
            post.post.imageUuid,
            post.post.createdAt.toInstant().toString(),
            UserJsonAdapter().userToJson(post.user)
        )
    }
}