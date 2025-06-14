/*
 * Copyright (C) 2024 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package co.aospa.dolby.xiaomi.geq.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.settingslib.spa.framework.theme.settingsBackground
import com.android.settingslib.spa.framework.theme.SettingsDimension
import co.aospa.dolby.xiaomi.R

@Composable
fun EqualizerScreen(
    viewModel: EqualizerViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SettingsDimension.itemPadding)
            .then(modifier),
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxHeight()
        ) {
            PresetSelector(viewModel = viewModel)
            EqualizerBands(viewModel = viewModel)
        }
    }
}
