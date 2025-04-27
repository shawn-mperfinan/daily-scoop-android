package com.dailyscoop.app.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.dailyscoop.app.navigation.TopLevelDestination
import com.dailyscoop.app.navigation.topLevelDestinations
import com.dailyscoop.app.ui.core.PhonePreviews
import com.dailyscoop.app.ui.theme.DailyScoopTheme
import com.dailyscoop.app.ui.theme.daily_scoop_eastern_blue
import com.dailyscoop.app.ui.theme.gilroyMedium
import com.dailyscoop.app.ui.theme.gilroyRegular
import com.dailyscoop.app.utilities.isSelectedMainLevelDestination
import kotlin.reflect.KClass

const val BOTTOM_BAR_TEST_TAG = "BottomBar"

@Composable
fun DailyscoopBottomBar(
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestinationRoute: (KClass<*>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    NavigationBar(
        tonalElevation = 0.dp,
        containerColor = colorScheme.surface,
        modifier = modifier.testTag(BOTTOM_BAR_TEST_TAG),
    ) {
        destinations.forEach { destination ->
            val isDestinationSelected = currentDestination.isSelectedMainLevelDestination(destination.route)
            val navBarItemIcon = if (isDestinationSelected) destination.selectedIcon else destination.unselectedIcon
            val labelTextFontFamily = if (isDestinationSelected) gilroyMedium else gilroyRegular
            val labelTextStyle = MaterialTheme.typography.labelMedium.copy(fontFamily = labelTextFontFamily)

            NavigationBarItem(
                modifier = Modifier.testTag(destination.testTag),
                colors =
                    NavigationBarItemDefaults.colors(
                        indicatorColor = colorScheme.secondaryContainer,
                        selectedIconColor = daily_scoop_eastern_blue,
                        selectedTextColor = colorScheme.onSurface,
                        unselectedIconColor = colorScheme.surfaceVariant,
                        unselectedTextColor = colorScheme.outline,
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

@PhonePreviews
@Composable
fun DailyScoopBottomBarPreview() {
    DailyScoopTheme {
        DailyscoopBottomBar(
            destinations = topLevelDestinations,
            currentDestination = null,
            onNavigateToDestinationRoute = {},
            modifier = Modifier.padding(top = 24.dp),
        )
    }
}
