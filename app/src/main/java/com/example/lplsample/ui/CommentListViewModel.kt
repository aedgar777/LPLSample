package com.example.lplsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lplsample.domain.Comment
import com.example.lplsample.domain.interactors.CommentInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CommentListViewModel(private val commentInteractor: CommentInteractor) : ViewModel(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext =  Dispatchers.IO + job
    private val _uiState = MutableStateFlow<CommentListUiState>(CommentListUiState.Loading(emptyList()))
    val uiState: MutableStateFlow<CommentListUiState> = _uiState


    init {
        getPosts()
    }

    fun getPosts() {

        _uiState.value = CommentListUiState.Loading(emptyList())

        viewModelScope.launch(coroutineContext) {
            val result = commentInteractor.getPostsFromRemote()

            when (result) {
                is com.example.lplsample.domain.Result.Success -> {
                    _uiState.value = CommentListUiState.Success(result.data)
                }
                is com.example.lplsample.domain.Result.Error -> {
                    _uiState.value = CommentListUiState.Error(result.exception.message ?: "Unknown Error")
                }
            }
        }
    }
}


sealed class CommentListUiState{
    data class Loading(val comments: List<Comment>): CommentListUiState()
    data class Success(val comments: List<Comment>): CommentListUiState()
    data class Error(val message: String): CommentListUiState()

}