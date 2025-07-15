package com.raulastete.spendless.screen.transactions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raulastete.spendless.ui.theme.SpendLessTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionsRoot(
    viewModel: TransactionsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TransactionsScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun TransactionsScreen(
    state: TransactionsState,
    onAction: (TransactionsAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    SpendLessTheme {
        TransactionsScreen(
            state = TransactionsState(),
            onAction = {}
        )
    }
}