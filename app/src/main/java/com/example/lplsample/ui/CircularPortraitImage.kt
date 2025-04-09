package com.example.lplsample.ui

import android.net.Uri
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lplsample.R


@Composable
fun CircularPortraitImage(modifier: Modifier = Modifier,imageUri: Uri? = null) {
         AsyncImage(
             model = imageUri ?: R.drawable.baseline_person_24,
             contentDescription = "Profile Image",
             modifier = modifier
                 .size(40.dp)
                 .clip(CircleShape),
             contentScale = ContentScale.Crop,
         )
}