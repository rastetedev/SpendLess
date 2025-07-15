package com.raulastete.spendless.di

import com.raulastete.spendless.screen.settings.preferences.PreferencesViewModel
import com.raulastete.spendless.screen.settings.root.SettingsRootViewModel
import com.raulastete.spendless.screen.settings.security.SecurityViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SettingsRootViewModel)
    viewModelOf(::SecurityViewModel)
    viewModelOf(::PreferencesViewModel)
}