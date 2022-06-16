package com.sshpro.threepeeks.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun ProgressView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .testTag(TestTags.LOADING_VIEW)
    ) {
        CircularProgressIndicator(
            strokeWidth = 8.dp,
            modifier = Modifier.fillMaxSize(0.25f)
        )
    }
}