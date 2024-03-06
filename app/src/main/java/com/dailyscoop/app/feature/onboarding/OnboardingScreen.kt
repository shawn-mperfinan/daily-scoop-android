package com.dailyscoop.app.feature.onboarding

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dailyscoop.app.R
import com.dailyscoop.app.ui.theme.daily_scoop_eastern_blue
import com.dailyscoop.app.ui.theme.md_theme_light_surface
import com.dailyscoop.app.utilities.EIGHT_PADDING
import com.dailyscoop.app.utilities.EMPTY_STRING
import com.dailyscoop.app.utilities.ONBOARDING_IMAGE_ALPHA
import kotlinx.coroutines.launch

@Composable
internal fun OnboardingScreenConnector() {
    // TODOs: Inject VM here once finalized
    OnboardingScreen(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardingScreen(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val onboardingScreenCounts = onboardingScreenItems.size
    val pagerState = rememberPagerState { onboardingScreenCounts }

    val isLastOnboardingScreen = pagerState.currentPage == onboardingScreenItems.lastIndex
    val shouldShowBackButton = pagerState.currentPage > 0 && !isLastOnboardingScreen
    val shouldShowNextButton = !isLastOnboardingScreen
    val shouldShowStartReadingButton = pagerState.currentPage == onboardingScreenItems.lastIndex

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        HorizontalPager(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.Top,
            state = pagerState,
            userScrollEnabled = !isLastOnboardingScreen,
        ) { currentPage ->
            OnboardingPagerContent(currentOnboardingScreen = onboardingScreenItems[currentPage])
        }

        PageIndicators(
            pageCount = onboardingScreenCounts,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(vertical = 40.dp),
        )

        if (shouldShowStartReadingButton) {
            StartReadingButton()
        } else {
            OnboardingNavigationButtons(
                shouldShowBackButton = shouldShowBackButton,
                shouldShowNextButton = shouldShowNextButton,
                onBackClick = {
                    scope.launch {
                        val previousPage = pagerState.currentPage - 1
                        pagerState.scrollToPage(previousPage)
                    }
                },
                onNextClick = {
                    scope.launch {
                        val nextPage = pagerState.currentPage + 1
                        pagerState.scrollToPage(nextPage)
                    }
                },
            )
        }
    }
}

@Composable
private fun OnboardingPagerContent(currentOnboardingScreen: OnboardingScreenItem) {
    val imageTopPadding = (LocalConfiguration.current.screenHeightDp.div(EIGHT_PADDING)).dp

    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // TODO - update aspect ratio for later support on multiple screen densities
        Image(
            modifier =
                Modifier
                    .aspectRatio(ONBOARDING_IMAGE_ALPHA)
                    .fillMaxWidth()
                    .padding(top = imageTopPadding),
            painter = painterResource(id = currentOnboardingScreen.media),
            contentDescription = null,
        )

        Text(
            modifier = Modifier.padding(vertical = 34.dp),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            text = stringResource(id = currentOnboardingScreen.title),
        )

        Text(
            style =
                MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                ),
            textAlign = TextAlign.Center,
            text = stringResource(id = currentOnboardingScreen.description),
        )
    }
}

@Composable
private fun PageIndicators(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        repeat(pageCount) { currentPageIndicated ->
            SingleDotIndicator(isSelected = currentPageIndicated == currentPage)
        }
    }
}

@Composable
private fun SingleDotIndicator(isSelected: Boolean) {
    val indicatorWidth = animateDpAsState(targetValue = if (isSelected) 20.dp else 8.dp, label = EMPTY_STRING)
    val indicatorBackgroundColor =
        if (isSelected) daily_scoop_eastern_blue else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)

    Box(
        modifier =
            Modifier
                .padding(horizontal = 2.dp)
                .width(indicatorWidth.value)
                .height(8.dp)
                .clip(CircleShape)
                .background(indicatorBackgroundColor),
    )
}

@Composable
private fun StartReadingButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors().copy(containerColor = daily_scoop_eastern_blue),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(20.dp)),
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.labelLarge.copy(color = md_theme_light_surface),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.start_reading_button_label),
        )
    }
}

@Composable
private fun OnboardingNavigationButtons(
    shouldShowBackButton: Boolean,
    shouldShowNextButton: Boolean,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 10.dp),
        horizontalArrangement = if (shouldShowBackButton) Arrangement.SpaceBetween else Arrangement.End,
    ) {
        if (shouldShowBackButton) {
            NavigationButton(
                buttonLabel = stringResource(id = R.string.back_button_label),
                onClick = onBackClick,
            )
        }

        if (shouldShowNextButton) {
            NavigationButton(
                buttonLabel = stringResource(id = R.string.next_button_label),
                fillColor = daily_scoop_eastern_blue,
                onClick = onNextClick,
            )
        }
    }
}

@Composable
private fun NavigationButton(
    buttonLabel: String,
    fillColor: Color = Color.Transparent,
    onClick: () -> Unit,
) {
    val textColor =
        if (fillColor == Color.Transparent) {
            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        } else {
            md_theme_light_surface
        }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors().copy(containerColor = fillColor),
        modifier =
            Modifier
                .width(86.dp)
                .clip(RoundedCornerShape(20.dp)),
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.labelLarge.copy(color = textColor),
            textAlign = TextAlign.Center,
            text = buttonLabel,
        )
    }
}
