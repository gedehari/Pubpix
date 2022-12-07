package com.gedehari.pubpix.network

import com.gedehari.pubpix.model.post.Post
import com.gedehari.pubpix.model.post.PostJson
import com.gedehari.pubpix.model.post.PostWithUser
import com.squareup.moshi.FromJson
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
}