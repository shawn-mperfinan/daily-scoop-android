package com.dailyscoop.app.ui.core

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 * Multi-preview annotation that represents small and large phone device sizes.
 * Add this annotation to a composable to render various devices.
 */
@Preview(
    name = "SmallPhonePreviewLight",
    device = "spec:width=320dp,height=533dp,dpi=240",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xF5F5F7,
)
@Preview(
    name = "SmallPhonePreviewDark",
    device = "spec:width=320dp,height=533dp,dpi=240",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0x1F1D2B,
)
@Preview(
    name = "LargePhonePreviewLight",
    device = "spec:width=360dp,height=780dp,dpi=560",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xF5F5F7,
)
@Preview(
    name = "LargePhonePreviewDark",
    device = "spec:width=360dp,height=780dp,dpi=560",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0x1F1D2B,
)
annotation class PhonePreviews
