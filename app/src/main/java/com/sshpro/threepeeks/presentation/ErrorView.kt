package com.sshpro.threepeeks.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorView(message: String?, modifier: Modifier = Modifier) {
    Text(
        text = "Error $message",
        modifier = modifier.testTag(TestTags.ERROR_VIEW),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h3
    )
}