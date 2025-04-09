package com.example.lplsample.domain.interactors

import com.example.lplsample.domain.Comment
import com.example.lplsample.domain.Result

interface CommentInteractor {
    suspend fun getPostsFromRemote(): Result<List<Comment>>
}