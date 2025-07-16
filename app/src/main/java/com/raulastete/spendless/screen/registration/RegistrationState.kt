package com.raulastete.spendless.screen.registration

import com.raulastete.spendless.screen.settings.preferences.DecimalSeparator
import com.raulastete.spendless.screen.settings.preferences.ExpensesFormat
import com.raulastete.spendless.screen.settings.preferences.ThousandSeparator

data class RegistrationUiState(
    val username: String = "",
    val completePin: String = "",
    val completePinConfirmation: String = "",
    val expensesFormat: ExpensesFormat = ExpensesFormat.NEGATIVE,
    val decimalSeparator: DecimalSeparator = DecimalSeparator.PERIOD,
    val thousandSeparator: ThousandSeparator = ThousandSeparator.PERIOD,
    val registrationStep: RegistrationStep = RegistrationStep.USERNAME
)

enum class RegistrationStep {
    USERNAME, PIN, PIN_CONFIRMATION, PREFERENCES
}