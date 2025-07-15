package com.raulastete.spendless.screen.settings.root

import androidx.lifecycle.ViewModel

class SettingsRootViewModel : ViewModel() {

    fun onAction(action: SettingsRootAction) {
        when (action) {
            SettingsRootAction.OnClickLogOut -> logout()
            else -> Unit
        }
    }

    fun logout() {

    }

}