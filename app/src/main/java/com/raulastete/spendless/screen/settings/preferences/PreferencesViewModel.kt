package com.raulastete.spendless.screen.settings.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class PreferencesViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(PreferencesState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = PreferencesState()
        )

    fun onAction(action: PreferencesAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

}