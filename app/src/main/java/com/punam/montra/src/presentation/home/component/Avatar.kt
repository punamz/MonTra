package com.punam.montra.src.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


@Composable
fun Avatar(
    avatarUrl: String?,
) {
    val modifier = Modifier
        .size(32.dp)
        .clip(CircleShape)
        .border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.primary,
            CircleShape
        )
        .background(MaterialTheme.colorScheme.onPrimary)
        .padding(3.dp)
        .clip(CircleShape)
    if (avatarUrl == null)
        Icon(
            imageVector = Icons.Rounded.Person,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(2.dp)
        )
    else
        Image(
            painter = rememberAsyncImagePainter(avatarUrl),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier,
        )
}