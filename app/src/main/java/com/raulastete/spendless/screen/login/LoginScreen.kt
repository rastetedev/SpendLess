package com.raulastete.spendless.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raulastete.spendless.R
import com.raulastete.spendless.screen.component.AppLogo
import com.raulastete.spendless.screen.component.AppTextButton
import com.raulastete.spendless.screen.component.AppTextField
import com.raulastete.spendless.screen.component.PrimaryButton
import com.raulastete.spendless.ui.theme.SpendLessTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun LoginContent(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
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
                stringResource(R.string.login_title),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                stringResource(R.string.login_subtitle),
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
            Spacer(Modifier.height(36.dp))
            AppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = "",
                onValueChange = {},
                placeholder = stringResource(R.string.username_placeholder)
            )
            Spacer(Modifier.height(16.dp))
            AppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = "",
                onValueChange = {},
                placeholder = stringResource(R.string.pin_placeholder)
            )
            Spacer(Modifier.height(24.dp))
            PrimaryButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                text = stringResource(R.string.login_button)
            ) { }
            Spacer(Modifier.height(28.dp))
            AppTextButton(text = stringResource(R.string.registration_button)) { }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SpendLessTheme {
        LoginContent(
            state = LoginState(),
            onAction = {}
        )
    }
}