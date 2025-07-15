package com.raulastete.spendless.screen.settings.root

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raulastete.spendless.R
import com.raulastete.spendless.screen.component.CardContainer
import com.raulastete.spendless.ui.theme.SpendLessTheme
import com.raulastete.spendless.ui.theme.SurfaceContainerLow
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsRootScreen(
    viewModel: SettingsRootViewModel = koinViewModel(),
    navigateToPreferences: () -> Unit,
    navigateToSecurity: () -> Unit
) {
    SettingsRootContent(
        onAction = {
            when (it) {
                SettingsRootAction.OnClickPreferences -> navigateToPreferences()
                SettingsRootAction.OnClickSecurity -> navigateToSecurity()
                else -> {
                    viewModel.onAction(it)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsRootContent(
    onAction: (SettingsRootAction) -> Unit,
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
                    Text(stringResource(R.string.settings_title), style = MaterialTheme.typography.titleLarge)
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
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CardContainer(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Column {
                    SettingsItem(
                        modifier = Modifier.fillMaxWidth(),
                        icon = Icons.Outlined.Settings,
                        text = stringResource(R.string.preferences_title)
                    ) {
                        onAction(SettingsRootAction.OnClickPreferences)
                    }

                    SettingsItem(
                        modifier = Modifier.fillMaxWidth(),
                        icon = Icons.Outlined.Lock,
                        text = stringResource(R.string.security_title)
                    ) {
                        onAction(SettingsRootAction.OnClickSecurity)
                    }
                }
            }

            CardContainer(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                SettingsItem(
                    modifier = Modifier.fillMaxWidth(),
                    icon = Icons.AutoMirrored.Outlined.Logout,
                    text = stringResource(R.string.logout_option),
                    iconBackground = MaterialTheme.colorScheme.errorContainer,
                    textColor = MaterialTheme.colorScheme.error
                ) {
                    onAction(SettingsRootAction.OnClickLogOut)
                }
            }
        }
    }
}



@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    iconBackground: Color = SurfaceContainerLow,
    textColor: Color =  MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            Modifier
                .size(40.dp)
                .background(iconBackground, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = textColor
            )
        }
        Text(text = text, color = textColor, style = MaterialTheme.typography.labelMedium)
    }
}

@Preview
@Composable
private fun Preview() {
    SpendLessTheme {
        SettingsRootContent(
            onAction = {}
        )
    }
}