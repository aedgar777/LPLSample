package com.example.lplsample.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lplsample.domain.Comment


@Composable
fun CommentList(comments: List<Comment>) {

    LazyColumn{
        items(comments) { comment ->
            CommentListItem(comment)
        }
    }

}
