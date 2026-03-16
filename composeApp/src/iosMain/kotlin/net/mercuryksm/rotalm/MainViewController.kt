package net.mercuryksm.rotalm

import androidx.compose.ui.window.ComposeUIViewController
import net.mercuryksm.rotalm.di.platformModule

fun MainViewController() = ComposeUIViewController { App(platformModule = platformModule) }