package com.dailyscoop.app.feature.onboarding

import com.dailyscoop.app.R

enum class OnboardingScreenItem(
    val media: Int,
    val title: Int,
    val description: Int,
) {
    DAILY_NEWS_HEADLINE(
        media = R.drawable.img_headline_onboarding,
        title = R.string.home_onboarding_label,
        description = R.string.home_onboarding_description,
    ),
    SEARCH_VARIETIES_OF_NEWS(
        media = R.drawable.img_search_onboarding,
        title = R.string.search_onboarding_label,
        description = R.string.search_onboarding_description,
    ),
    BOOKMARK_INTERESTING_ARTICLE(
        media = R.drawable.img_bookmark_onboarding,
        title = R.string.bookmark_onboarding_label,
        description = R.string.bookmark_onboarding_description,
    ),
}

/**
 * List of onboarding screen features that can be utilized within the app
 */
val onboardingScreenEntries: List<OnboardingScreenItem> = OnboardingScreenItem.entries
