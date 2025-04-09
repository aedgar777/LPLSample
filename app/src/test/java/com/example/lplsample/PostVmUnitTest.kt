package com.example.lplsample

import com.example.lplsample.domain.Comment
import com.example.lplsample.domain.Result
import com.example.lplsample.domain.interactors.CommentInteractor
import com.example.lplsample.ui.CommentListUiState
import com.example.lplsample.ui.CommentListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
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
    private val testScope = TestScope(testDispatcher)

    private lateinit var commentInteractor: CommentInteractor
    private lateinit var viewModel: CommentListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        commentInteractor = mock()
        viewModel = CommentListViewModel(commentInteractor)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPosts updates uiState to Success when result is Success`() = testScope.runTest {
        // Given
        val comments = listOf(Comment(1, 2, "name", "email", "body"))
        val successResult = Result.Success(comments)
        doReturn(successResult).`when`(commentInteractor).getPostsFromRemote()

        // When
        viewModel.getPosts()

        // Then
        verify(commentInteractor).getPostsFromRemote()
        assertEquals(CommentListUiState.Success(comments), viewModel.uiState.value)
    }

    @Test
    fun `getPosts updates uiState to Error when result is Error`() = testScope.runTest {
        // Given
        val errorMessage = "Test error message"
        val errorResult = Result.Error(Exception(errorMessage))
        doReturn(errorResult).`when`(commentInteractor).getPostsFromRemote()

        // When
        viewModel.getPosts()

        // Then
        verify(commentInteractor).getPostsFromRemote()
        assertEquals(CommentListUiState.Error(errorMessage), viewModel.uiState.value)
    }

    @Test
    fun `getPosts updates uiState to Loading initially`() = testScope.runTest {
        // Given
        val comments = listOf(Comment(1, 2,  "name", "email", "body"))
        val successResult = Result.Success(comments)
        doReturn(successResult).`when`(commentInteractor).getPostsFromRemote()

        // When
        // (Already called in init)

        // Then
        assertEquals(CommentListUiState.Loading(emptyList<Comment>()), (viewModel.uiState as MutableStateFlow<*>).replayCache.firstOrNull())

        // Call again to make sure it still updates to loading.
        viewModel.getPosts()

        assertEquals(CommentListUiState.Loading(emptyList<Comment>()),
            (viewModel.uiState as MutableStateFlow<*>).replayCache[1]
        )
    }

    @Test
    fun `getPosts handles unknown error message`() = testScope.runTest {
        // Given
        val errorResult = Result.Error(Exception())
        doReturn(errorResult).`when`(commentInteractor).getPostsFromRemote()

        // When
        viewModel.getPosts()


        // Then
        verify(commentInteractor).getPostsFromRemote()
        assertEquals(CommentListUiState.Error("Unknown Error"), viewModel.uiState.value)
    }
}