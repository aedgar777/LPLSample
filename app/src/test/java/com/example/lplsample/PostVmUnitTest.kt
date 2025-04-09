package com.example.lplsample

import com.example.lplsample.domain.Comment
import com.example.lplsample.domain.Result
import com.example.lplsample.domain.interactors.CommentInteractor
import com.example.lplsample.ui.CommentListUiState
import com.example.lplsample.ui.CommentListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class CommentListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPosts updates uiState to Success when result is Success`() = runTest {
        val commentInteractor: CommentInteractor = mock()
        val viewModel = CommentListViewModel(commentInteractor)

        val comments = listOf(Comment(1, 2, "name", "email", "body"))
        val successResult = Result.Success(comments)
        doReturn(successResult).`when`(commentInteractor).getPostsFromRemote()

        viewModel.getPosts()

        val emittedState = viewModel.uiState.first { it is CommentListUiState.Success || it is CommentListUiState.Error }
        verify(commentInteractor).getPostsFromRemote()
        assertEquals(CommentListUiState.Success(comments), emittedState)
    }

    @Test
    fun `getPosts updates uiState to Error when result is Error`() = runTest {
        val commentInteractor: CommentInteractor = mock()
        val viewModel = CommentListViewModel(commentInteractor)

        val errorMessage = "Test error message"
        val errorResult = Result.Error(Exception(errorMessage))
        doReturn(errorResult).`when`(commentInteractor).getPostsFromRemote()

        viewModel.getPosts()

        val emittedState = viewModel.uiState.first { it is CommentListUiState.Success || it is CommentListUiState.Error }
        verify(commentInteractor).getPostsFromRemote()
        assertEquals(CommentListUiState.Error(errorMessage), emittedState)
    }


    @Test
    fun `getPosts handles unknown error message`() = runTest {
        val commentInteractor: CommentInteractor = mock()
        val viewModel = CommentListViewModel(commentInteractor)

        val errorResult = Result.Error(Exception())
        doReturn(errorResult).`when`(commentInteractor).getPostsFromRemote()

        viewModel.getPosts()
        val emittedState = viewModel.uiState.first { it is CommentListUiState.Success || it is CommentListUiState.Error }
        verify(commentInteractor).getPostsFromRemote()
        assertEquals(CommentListUiState.Error("Unknown Error"), emittedState)
    }

    @Test
    fun `getPosts updates uiState to Loading initially`() = runTest {
        val commentInteractor: CommentInteractor = mock()
        val viewModel = CommentListViewModel(commentInteractor)

        val initialState = viewModel.uiState.value
        assertEquals(CommentListUiState.Loading(emptyList<Comment>()), initialState)
    }

}