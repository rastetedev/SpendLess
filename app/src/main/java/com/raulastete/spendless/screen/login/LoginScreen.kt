package com.raulastete.spendless.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raulastete.spendless.ui.theme.SpendLessTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginRoot(
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    SpendLessTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {}
        )
    }
}