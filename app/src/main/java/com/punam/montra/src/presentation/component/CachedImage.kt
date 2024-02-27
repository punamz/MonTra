package com.punam.montra.src.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.punam.montra.R
import kotlinx.coroutines.Dispatchers


@Composable
fun CachedImage(
    url: String?,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    colorFilter: ColorFilter? = null,
) {

    val context = LocalContext.current
    val placeholder = R.drawable.placeholder

    // Build an ImageRequest with Coil
    val listener = object : ImageRequest.Listener {}

    val imageRequest = ImageRequest.Builder(context)
        .data(url)
        .listener(listener)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(url)
        .diskCacheKey(url)
        .placeholder(placeholder)
        .error(placeholder)
        .fallback(placeholder)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = "Cache Image",
        modifier = modifier,
        contentScale = contentScale,
        colorFilter = colorFilter
    )
}