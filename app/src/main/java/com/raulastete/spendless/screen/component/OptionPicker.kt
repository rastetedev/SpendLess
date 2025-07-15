package com.raulastete.spendless.screen.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raulastete.spendless.ui.theme.PickerBackground
import com.raulastete.spendless.ui.theme.PickerContentInactive
import kotlin.collections.first

data class Option<T>(
    val id: Int,
    val label: String,
    val value: T
)

@Composable
fun OptionPicker(
    modifier: Modifier = Modifier,
    options: List<Option<*>>
) {

    val density = LocalDensity.current

    var optionSelected by remember { mutableStateOf(options.first().value) }

    var tabMaxWidth by remember { mutableStateOf(0.dp) }

    var selectedIndex by remember { mutableStateOf(0) }

    val offsetX by animateDpAsState(targetValue = (tabMaxWidth * selectedIndex) + (4.dp * selectedIndex))

    Box(
        modifier
            .background(
                PickerBackground,
                RoundedCornerShape(16.dp)
            )
            .padding(4.dp)
            .height(48.dp)
            .fillMaxWidth()
            .onGloballyPositioned {
                val widthPx = it.size.width
                val widthDp = with(density) { widthPx.toDp() }
                tabMaxWidth = (widthDp - 8.dp) / options.size
            },
        contentAlignment = Alignment.CenterStart
    ) {
        if (tabMaxWidth > 0.dp) {
            Box(
                Modifier
                    .offset(x = offsetX)
                    .width(tabMaxWidth)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        }

        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            options.forEachIndexed { index, option ->
                OptionTab(
                    text = option.label,
                    modifier = Modifier.width(tabMaxWidth),
                    active = optionSelected == option.value,
                    onClick = {
                        optionSelected = option.value
                        selectedIndex = options.indexOf(option)
                    }
                )
            }
        }
    }
}

@Composable
private fun OptionTab(
    modifier: Modifier = Modifier,
    text: String,
    active: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .fillMaxHeight()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = if (active) MaterialTheme.colorScheme.onSurface else PickerContentInactive
        )
    }
}