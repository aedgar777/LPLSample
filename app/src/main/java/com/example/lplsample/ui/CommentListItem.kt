package com.example.lplsample.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lplsample.domain.Comment


@Composable
fun CommentListItem(comment:Comment) {

    Row(verticalAlignment = Alignment.Top) {
        CircularPortraitImage()
        Column {
            Text(text = comment.id.toString())
            Text(text = comment.name)
            Text(text = comment.body)
        }
        Spacer(Modifier.weight(1f))
        Text(text = comment.email)
        }
}