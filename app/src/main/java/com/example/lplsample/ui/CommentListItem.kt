package com.example.lplsample.ui


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.lplsample.domain.Comment


@Composable
fun CommentListItem(comment:Comment) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            selectedImageUri = uri
        }
    )

    Row(modifier = Modifier.wrapContentHeight().padding(top = 16.dp, bottom = 16.dp, end = 8.dp), verticalAlignment = Alignment.Top) {
        CircularPortraitImage(modifier = Modifier.clickable{
            imagePickerLauncher.launch("image/*")
        },
            imageUri = selectedImageUri
        )
        Spacer(Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(text = comment.id.toString())
            Text(text = comment.name)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = comment.body)
        }
        Spacer(Modifier.width(8.dp))
        Text(modifier = Modifier.wrapContentWidth().horizontalScroll(rememberScrollState()), text = comment.email)
        }

    HorizontalDivider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
}