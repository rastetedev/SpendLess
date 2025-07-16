package com.raulastete.spendless.screen.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    var isFocused by remember { mutableStateOf(false) }

    val animatedBorderColor by animateColorAsState(
        targetValue = when {
            isFocused -> MaterialTheme.colorScheme.primary
            else -> Color.Transparent
        },
        animationSpec = tween(100, 0, LinearEasing),
        label = "Border color"
    )

    BasicTextField(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                MaterialTheme.colorScheme.surface,
            )
            .border(
                width = 1.dp,
                color = animatedBorderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .onFocusChanged {
                isFocused = it.isFocused
            },
        value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(Modifier.fillMaxWidth()){
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                innerTextField()
            }
        },
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
    )
}