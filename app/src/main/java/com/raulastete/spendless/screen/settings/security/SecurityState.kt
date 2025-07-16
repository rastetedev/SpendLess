package com.raulastete.spendless.screen.settings.security

import com.raulastete.spendless.screen.settings.component.Option

data class SecurityState(
    val biometricStatusOptions: List<Option<Boolean>> = listOf(
        Option(id = 1, label = "Enable", value = true),
        Option(id = 2, label = "Disable", value = false),
    ),
    val sessionExpiryMinutesDurationOptions: List<Option<Int>> = listOf(
        Option(id = 1, label = "5 min", value = 5),
        Option(id = 2, label = "15 min", value = 15),
        Option(id = 3, label = "30 min", value = 30),
        Option(id = 4, label = "1 hour", value = 60),
    ),
    val lockedOutSecondsDurationOption: List<Option<Int>> = listOf(
        Option(id = 1, label = "15s", value = 15),
        Option(id = 2, label = "30s", value = 30),
        Option(id = 3, label = "1 min", value = 60),
        Option(id = 4, label = "5 min", value = 300),
    )
)