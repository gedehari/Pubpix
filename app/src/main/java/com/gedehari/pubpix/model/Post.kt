package com.gedehari.pubpix.model

data class Post(
    val id: Int,
    val caption: String,
    val imageUuid: String,
    val createdAt: String,
    val author: User
)