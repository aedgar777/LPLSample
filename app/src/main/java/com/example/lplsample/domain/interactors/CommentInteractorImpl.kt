package com.example.lplsample.domain.interactors

import com.example.lplsample.domain.Comment
import com.example.lplsample.domain.CommentRepository
import com.example.lplsample.domain.Result

class CommentInteractorImpl(private val commentRepository: CommentRepository) : CommentInteractor {
    override suspend fun getPostsFromRemote(): Result<List<Comment>> {
        return commentRepository.getPosts()
    }
}