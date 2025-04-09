package com.example.lplsample.remote

import com.example.lplsample.data.PostResponse
import retrofit2.http.GET

interface CommentApi {
    @GET("posts/1/comments")
    suspend fun getPosts(): List<PostResponse>
}