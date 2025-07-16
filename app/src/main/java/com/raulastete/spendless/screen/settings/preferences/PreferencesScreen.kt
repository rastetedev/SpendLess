package com.raulastete.spendless.screen.settings.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raulastete.spendless.R
import com.raulastete.spendless.screen.component.CardContainer
import com.raulastete.spendless.screen.component.PrimaryButton
import com.raulastete.spendless.screen.component.SectionWithSpinner
import com.raulastete.spendless.screen.settings.component.SettingsSectionWithPicker
import com.raulastete.spendless.ui.theme.SpendLessTheme
import com.raulastete.spendless.ui.theme.onSurfaceVariant
import org.koin.androidx.compose.koinViewModel

@Composable
fun PreferencesScreen(
    viewModel: PreferencesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PreferencesContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferencesContent(
    state: PreferencesState,
    onAction: (PreferencesAction) -> Unit,
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
                        stringResource(R.string.preferences_title),
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

            CardContainer(
                Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text("-$10,382.45", style = MaterialTheme.typography.headlineLarge)
                    Text(
                        stringResource(R.string.preview_placeholder),
                        style = MaterialTheme.typography.bodySmall.copy(color = onSurfaceVariant)
                    )
                }
            }

            SettingsSectionWithPicker(
                title = stringResource(R.string.expenses_format_options_title),
                options = state.expensesFormatOptions
            )

            SectionWithSpinner(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.currency_options_title),
                items = state.currencyOptions,
                onItemSelected = {

                },
                selectedItem = state.currencyOptions.first(),
                itemToString = { currency ->
                    "${currency.icon} ${currency.label} (${currency.id})"
                }
            )

            SettingsSectionWithPicker(
                title = stringResource(R.string.decimal_separator_options_title),
                options = state.decimalSeparatorOptions
            )

            SettingsSectionWithPicker(
                title = stringResource(R.string.thousands_separator_options_title),
                options = state.thousandsSeparatorOptions
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
        PreferencesContent(
            state = PreferencesState(),
            onAction = {}
        )
    }
}