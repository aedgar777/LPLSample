package com.example.lplsample.data

import com.example.lplsample.domain.Comment
import com.example.lplsample.domain.CommentRepository
import com.example.lplsample.domain.Result
import com.example.lplsample.remote.CommentApi

class CommentRepositoryImpl(private val commentApi: CommentApi) : CommentRepository  {
    override suspend fun getPosts(): Result<List<Comment>> {

        val commentList:List<Comment> = CommentResponseToModelMapper.map(commentApi.getPosts())

        return when(commentList.isEmpty()){
            true -> Result.Error(Throwable("No Posts Returned"))
            false -> Result.Success(commentList)

        }
    }
}