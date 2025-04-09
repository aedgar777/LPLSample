package com.example.lplsample.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.lplsample.R


@Composable
fun CircularPortraitImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.baseline_person_24), // Replace with your default image resource
        contentDescription = "Default Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(CircleShape)
    )
}