package com.dailyscoop.app.utils

import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.core.content.ContextCompat
import kotlin.properties.ReadOnlyProperty

/**
 * Returns a delegated property for accessing a string resource from the test rule's activity.
 */
fun AndroidComposeTestRule<*, *>.stringResource(
    @StringRes resId: Int,
): ReadOnlyProperty<Any, String> =
    ReadOnlyProperty { _, _ ->
        activity.getString(resId)
    }

/**
 * Returns a delegated property for accessing a drawable resource from the test rule's activity.
 * @throws IllegalArgumentException if the drawable is not found.
 */
fun AndroidComposeTestRule<*, *>.drawableResource(
    @StringRes resId: Int,
): ReadOnlyProperty<Any, Drawable> =
    ReadOnlyProperty { _, _ ->
        ContextCompat.getDrawable(activity, resId)
            ?: throw IllegalArgumentException("Drawable resource not found: $resId")
    }
