package com.gedehari.pubpix.model.post

import com.gedehari.pubpix.model.user.UserJson

data class PostJson(
    val id: Int,
    val caption: String,
    val imageUuid: String,
    val createdAt: String,
    val author: UserJson
)