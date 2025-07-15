package com.raulastete.spendless.screen.settings.root

sealed interface SettingsRootAction {

    data object OnClickPreferences : SettingsRootAction
    data object OnClickSecurity : SettingsRootAction
    data object OnClickLogOut : SettingsRootAction
}