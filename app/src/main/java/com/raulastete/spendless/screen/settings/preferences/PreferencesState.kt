package com.raulastete.spendless.screen.settings.preferences

import com.raulastete.spendless.screen.settings.component.Option

data class PreferencesState(
    val preview: String = "",
    val expensesFormatOptions: List<Option<ExpensesFormat>> = listOf(
        Option(id = 1, label = "-$10", value = ExpensesFormat.NEGATIVE),
        Option(id = 2, label = "($10)", value = ExpensesFormat.BRACES),
    ),
    val decimalSeparatorOptions: List<Option<DecimalSeparator>> = listOf(
        Option(id = 1, label = "1.00", value = DecimalSeparator.PERIOD),
        Option(id = 2, label = "1,00", value = DecimalSeparator.COMMA)
    ),
    val thousandsSeparatorOptions: List<Option<ThousandSeparator>> = listOf(
        Option(id = 1, label = "1,000", value = ThousandSeparator.COMMA),
        Option(id = 2, label = "1.000", value = ThousandSeparator.PERIOD),
        Option(id = 3, label = "1 000", value = ThousandSeparator.SPACE),
    ),
    val currencyOptions: List<Currency> = listOf(
        Currency(id = "USD", icon = "$", label = "US Dollar"),
        Currency(id = "PEN", icon = "S/", label = "Peruvian Sol"),
        Currency(id = "EUR", icon = "€", label = "Euro"),
    )
)

enum class ExpensesFormat {
    NEGATIVE, BRACES
}

enum class DecimalSeparator {
    COMMA, PERIOD
}

enum class ThousandSeparator {
    COMMA, PERIOD, SPACE
}

data class Currency(
    val id: String,
    val icon: String,
    val label: String
)