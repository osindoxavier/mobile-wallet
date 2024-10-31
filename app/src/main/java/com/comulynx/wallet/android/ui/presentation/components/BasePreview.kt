package com.comulynx.wallet.android.ui.presentation.components

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Preview Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.DEFAULT
)
@Preview(
    name = "Preview Light Mode PIXEL 2",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_2
)
@Preview(
    name = "Preview Light Mode PHONE",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=411dp,height=891dp"
)
@Preview(
    name = "Preview Light Mode FOLDABLE",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=673dp,height=841dp"

)
@Preview(
    name = "Preview Light Mode TABLET",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=1280dp,height=800dp,dpi=240"

)
@Preview(
    name = "Preview Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.DEFAULT
)
@Preview(
    name = "Preview Dark Mode PIXEL 2",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_2
)
@Preview(
    name = "Preview Dark Mode PHONE",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:width=411dp,height=891dp"
)
@Preview(
    name = "Preview Dark Mode FOLDABLE",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:width=673dp,height=841dp"

)
@Preview(
    name = "Preview Dark Mode TABLET",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:width=1280dp,height=800dp,dpi=240"

)
annotation class BasePreview

@Preview(
    name = "Preview Light Mode TABLET",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=1280dp,height=800dp,dpi=240"

)
@Preview(
    name = "Preview Dark Mode TABLET",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:width=1280dp,height=800dp,dpi=240"

)
annotation class BaseTabletPreview