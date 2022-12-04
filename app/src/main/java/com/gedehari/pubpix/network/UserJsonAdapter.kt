package com.gedehari.pubpix.network

import com.gedehari.pubpix.model.user.User
import com.gedehari.pubpix.model.user.UserJson
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant
import java.util.Date

class UserJsonAdapter {
    @FromJson
    fun userFromJson(userJson: UserJson): User {
        return User(
            userJson.id,
            userJson.username,
            userJson.displayName,
            Date.from(Instant.parse(userJson.createdAt))
        )
    }

    @ToJson
    fun userToJson(user: User): UserJson {
        return UserJson(
            user.userId,
            user.username,
            user.displayName,
            user.createdAt.toInstant().toString()
        )
    }
}