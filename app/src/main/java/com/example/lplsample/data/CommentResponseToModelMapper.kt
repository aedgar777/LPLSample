package com.example.lplsample.data

import com.example.lplsample.domain.Comment

object CommentResponseToModelMapper:Mapper<List<PostResponse>, List<Comment>> {
    override fun map(from: List<PostResponse>): List<Comment> {
        return from.map {
            Comment(
                postId = it.postId,
                id = it.id,
                name = it.name,
                email = it.email,
                body = it.body
            )
        }
    }
}