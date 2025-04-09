package com.example.lplsample.domain

interface CommentRepository {
    suspend fun getPosts(): Result<List<Comment>>
}