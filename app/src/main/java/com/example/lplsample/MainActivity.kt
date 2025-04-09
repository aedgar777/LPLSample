package com.example.lplsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import com.example.lplsample.ui.CommentListUiState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lplsample.ui.CommentList
import com.example.lplsample.ui.CommentListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            val commentViewModel: CommentListViewModel by viewModel()
            val uiState: CommentListUiState by commentViewModel.uiState.collectAsState()
            commentViewModel.getPosts()


            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

                when (uiState) {
                    is CommentListUiState.Loading -> CircularProgressIndicator()

                    is CommentListUiState.Success -> CommentList((uiState as CommentListUiState.Success).comments)

                    is CommentListUiState.Error -> Text((uiState as CommentListUiState.Error).message.toString())

                }
            }

        }
    }
}


