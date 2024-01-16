package com.punam.montra.src.presentation.onboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.punam.montra.src.presentation.onboard.OnboardContent

@Composable
fun OnboardBody(
    content: OnboardContent,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {

        Image(
            painterResource(id = content.image),
            contentDescription = "onboard 1",
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = content.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 40.dp)
        )
        Text(
            text = content.description,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 15.dp)
        )
    }
}