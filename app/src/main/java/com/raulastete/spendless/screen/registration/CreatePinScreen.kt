package com.raulastete.spendless.screen.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raulastete.spendless.R
import com.raulastete.spendless.screen.component.AppLogo
import com.raulastete.spendless.screen.component.PinComponent
import com.raulastete.spendless.ui.theme.SpendLessTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreatePinScreen(
    viewModel: RegistrationViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CreatePinContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun CreatePinContent(
    state: RegistrationUiState,
    onAction: (RegistrationAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(36.dp))
            AppLogo()
            Spacer(Modifier.height(20.dp))
            Text(
                stringResource(R.string.create_pin_title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
                    .copy(color = MaterialTheme.colorScheme.onSurface)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                stringResource(R.string.create_pin_subtitle),
                style = MaterialTheme.typography.bodyMedium
                    .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
            Spacer(Modifier.height(36.dp))
            PinComponent(
                Modifier.fillMaxSize().padding(horizontal = 40.dp),
                onCompletePin = {

                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SpendLessTheme {
        CreateUsernameContent(
            state = RegistrationUiState(),
            onAction = {}
        )
    }
}