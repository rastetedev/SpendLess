package com.raulastete.spendless.screen.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {

    TextButton(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 32.dp)
    ) {
        Text(text, style = MaterialTheme.typography.titleMedium)
    }
}