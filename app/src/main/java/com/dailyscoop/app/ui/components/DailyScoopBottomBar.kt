package com.dailyscoop.app.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.dailyscoop.app.navigation.TopLevelDestination
import com.dailyscoop.app.navigation.topLevelDestinations
import com.dailyscoop.app.ui.theme.DailyScoopTheme
import com.dailyscoop.app.ui.theme.daily_scoop_eastern_blue
import com.dailyscoop.app.ui.theme.gilroyMedium
import com.dailyscoop.app.ui.theme.gilroyRegular
import com.dailyscoop.app.utilities.isSelectedMainLevelDestination

@Composable
fun DailyScoopBottomBar(
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestinationRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        tonalElevation = 0.dp,
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val isDestinationSelected = currentDestination.isSelectedMainLevelDestination(destination)
            val navBarItemIcon = if (isDestinationSelected) destination.selectedIcon else destination.unselectedIcon
            val labelTextStyle =
                MaterialTheme.typography.labelMedium.copy(
                    fontFamily = if (isDestinationSelected) gilroyMedium else gilroyRegular,
                )

            NavigationBarItem(
                colors =
                    NavigationBarItemDefaults.colors(
                        selectedIconColor = daily_scoop_eastern_blue,
                        unselectedIconColor = MaterialTheme.colorScheme.surfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.outline,
                    ),
                selected = isDestinationSelected,
                label = {
                    Text(
                        stringResource(id = destination.label),
                        style = labelTextStyle,
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(navBarItemIcon),
                        contentDescription = null,
                    )
                },
                onClick = { onNavigateToDestinationRoute(destination.route) },
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xF5F5F7,
    name = "PreviewLight",
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0x1F1D2A,
    name = "PreviewDark",
)
@Composable
fun DailyScoopBottomBarPreview() {
    DailyScoopTheme {
        DailyScoopBottomBar(
            destinations = topLevelDestinations,
            currentDestination = null,
            onNavigateToDestinationRoute = {},
            modifier = Modifier.padding(top = 24.dp),
        )
    }
}
