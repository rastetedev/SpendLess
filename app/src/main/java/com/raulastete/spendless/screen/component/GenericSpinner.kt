package com.raulastete.spendless.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import com.raulastete.spendless.ui.theme.SpendLessTheme

@Composable
fun <T> GenericSpinner(
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    itemToString: (T) -> String = { it.toString() } // Lambda to get string representation
) {
    var expanded by remember { mutableStateOf(false) }
    var textfieldSize by remember { mutableStateOf(Size.Zero) } // To make DropdownMenu same width

    val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown

    Box(modifier = modifier) {

        OutlinedTextField( // You can use Text or any other Composable to display the selected item
            value = itemToString(selectedItem),
            onValueChange = { /* Read-only, selection handled by DropdownMenu */ },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(16.dp)
                )
                .onGloballyPositioned { coordinates ->
                    // Get the size of the TextField to match DropdownMenu width
                    textfieldSize = coordinates.size.toSize()
                },
            readOnly = true,
            trailingIcon = {
                Icon(
                    icon,
                    "contentDescription",
                    Modifier.clickable { expanded = !expanded },
                    MaterialTheme.colorScheme.onSurface
                )
            },
            colors = OutlinedTextFieldDefaults.colors( // Optional: Customize colors
                focusedBorderColor = Transparent,
                unfocusedBorderColor = Transparent
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() }), // Match width
            properties = PopupProperties(focusable = false) // Optional: for specific behaviors
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = itemToString(item)) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

data class FruitItem(val id: Int, val name: String, val emoji: String)


@Preview(showBackground = true)
@Composable
fun DefaultSpinnerPreview() {
    SpendLessTheme {
        val fruitItems = remember {
            listOf(
                FruitItem(1, "Manzana", "\uD83C\uDF4E"),
                FruitItem(2, "Banana", "\uD83C\uDF4C"),
                FruitItem(3, "Naranja", "\uD83C\uDF4A"),
                FruitItem(4, "Uva", "\uD83C\uDF47")
            )
        }

        var selectedFruit by remember { mutableStateOf(fruitItems.first()) }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Selecciona una fruta:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            GenericSpinner(
                items = fruitItems,
                selectedItem = selectedFruit,
                onItemSelected = { selectedFruit = it },
                itemToString = { "${it.emoji} ${it.name}" }, // Custom string representation
                modifier = Modifier.width(250.dp) // Set a width for the spinner
            )

            Spacer(Modifier.height(16.dp))
            Text("Seleccionado: ${selectedFruit.emoji} ${selectedFruit.name}")

            // --- Another example with simple strings ---
            Spacer(Modifier.height(32.dp))
            val colors = remember { listOf("Rojo", "Verde", "Azul", "Amarillo") }
            var selectedColor by remember { mutableStateOf(colors.first()) }

            Text("Selecciona un color:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            GenericSpinner(
                items = colors,
                selectedItem = selectedColor,
                onItemSelected = { selectedColor = it },
                // itemToString will use default .toString() for String
                modifier = Modifier.width(200.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text("Seleccionado: $selectedColor")
        }
    }
}