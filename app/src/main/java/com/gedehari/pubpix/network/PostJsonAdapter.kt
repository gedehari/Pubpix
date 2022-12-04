package com.gedehari.pubpix.network

import com.gedehari.pubpix.model.post.Post
import com.gedehari.pubpix.model.post.PostJson
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant
import java.util.Date

class PostJsonAdapter {
    @FromJson
    fun postFromJson(postJson: PostJson): Post {
        return Post(
            postJson.id,
            postJson.caption,
            postJson.imageUuid,
            Date.from(Instant.parse(postJson.createdAt)),
            UserJsonAdapter().userFromJson(postJson.author)
        )
    }

    @ToJson
    fun postToJson(post: Post): PostJson {
        return PostJson(
            post.postId,
            post.caption,
            post.imageUuid,
            post.createdAt.toInstant().toString(),
            UserJsonAdapter().userToJson(post.author)
        )
    }
}