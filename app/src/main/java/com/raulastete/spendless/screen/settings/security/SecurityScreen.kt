package com.raulastete.spendless.screen.settings.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raulastete.spendless.R
import com.raulastete.spendless.screen.component.PrimaryButton
import com.raulastete.spendless.screen.settings.component.SettingsSectionWithPicker
import com.raulastete.spendless.ui.theme.SpendLessTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SecurityScreen(
    viewModel: SecurityViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SecurityContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityContent(
    state: SecurityState,
    onAction: (SecurityAction) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        stringResource(R.string.security_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingsSectionWithPicker(
                title = stringResource(R.string.biometrics_option_title),
                options = state.biometricStatusOptions
            )

            SettingsSectionWithPicker(
                title = stringResource(R.string.session_expiry_option_title),
                options = state.sessionExpiryMinutesDurationOptions
            )

            SettingsSectionWithPicker(
                title = stringResource(R.string.locked_out_option_title),
                options = state.lockedOutSecondsDurationOption
            )

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = stringResource(R.string.save_button)
            ) {

            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SpendLessTheme {
        SecurityContent(
            state = SecurityState(),
            onAction = {}
        )
    }
}