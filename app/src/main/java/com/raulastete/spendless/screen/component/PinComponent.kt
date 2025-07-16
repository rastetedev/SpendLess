package com.raulastete.spendless.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.raulastete.spendless.R
import com.raulastete.spendless.ui.theme.OnBackground
import com.raulastete.spendless.ui.theme.PickerContentInactive
import com.raulastete.spendless.ui.theme.PrimaryFixed

val pinList = listOf(
    Pin.Number(1),
    Pin.Number(2),
    Pin.Number(3),
    Pin.Number(4),
    Pin.Number(5),
    Pin.Number(6),
    Pin.Number(7),
    Pin.Number(8),
    Pin.Number(9),
    Pin.Empty,
    Pin.Number(0),
    Pin.RemoveDigit,
)

@Composable
fun PinComponent(
    modifier: Modifier = Modifier,
    pinLength: Int = 5,
    onCompletePin: (String) -> Unit
) {
    var completePin by remember { mutableStateOf("") }

    Column {
        val pinListRemembered = remember { pinList }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Spacer(Modifier.weight(1f))
            repeat((1..pinLength).count()) { index ->
                MaskedDigit(isActive = index < completePin.length)
            }
            Spacer(Modifier.weight(1f))
        }

        Spacer(Modifier.height(32.dp))

        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(pinListRemembered) { index, pin ->
                PinButton(
                    modifier = Modifier.height(100.dp),
                    pin = pin,
                    onClickNumber = { number ->
                        completePin += number.toString()
                        if (completePin.length == pinLength) {
                            onCompletePin(completePin)
                        }
                    },
                    onClickDeleteLastDigit = {
                        completePin = completePin.dropLast(1)
                    }
                )
            }
        }
    }

}

@Composable
fun MaskedDigit(isActive: Boolean) {
    Box(
        Modifier
            .size(18.dp)
            .background(
                color = if (isActive) MaterialTheme.colorScheme.primary
                else OnBackground.copy(alpha = 0.12f),
                shape = CircleShape
            )
    )
}

@Composable
fun PinButton(
    modifier: Modifier = Modifier,
    pin: Pin,
    onClickNumber: (number: Int) -> Unit,
    onClickDeleteLastDigit: () -> Unit
) {
    Box(
        contentAlignment = Center,
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .clickable(enabled = pin !is Pin.Empty) {
                when (pin) {
                    is Pin.Number -> onClickNumber(pin.value)
                    Pin.RemoveDigit -> onClickDeleteLastDigit()
                    else -> Unit
                }
            }
            .background(
                color = when (pin) {
                    is Pin.Number -> PrimaryFixed
                    Pin.RemoveDigit -> PrimaryFixed.copy(alpha = 0.3f)
                    Pin.Empty -> Transparent
                },
                shape = RoundedCornerShape(32.dp)
            )
    ) {
        when (pin) {
            is Pin.Number ->
                Text(
                    text = pin.value.toString(),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = PickerContentInactive
                    )
                )

            Pin.RemoveDigit -> {
                Icon(
                    ImageVector.vectorResource(R.drawable.delete_digit),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            Pin.Empty -> {}
        }
    }
}

sealed interface Pin {

    data class Number(val value: Int) : Pin
    data object RemoveDigit : Pin
    data object Empty : Pin
}