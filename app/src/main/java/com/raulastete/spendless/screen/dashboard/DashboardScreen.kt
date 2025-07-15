 package com.raulastete.spendless.screen.dashboard import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
 import com.raulastete.spendless.ui.theme.SpendLessTheme
 import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardRoot(
    viewModel: DashboardViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    DashboardScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun DashboardScreen(
    state: DashboardState,
    onAction: (DashboardAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    SpendLessTheme {
        DashboardScreen(
            state = DashboardState(),
            onAction = {}
        )
    }
}