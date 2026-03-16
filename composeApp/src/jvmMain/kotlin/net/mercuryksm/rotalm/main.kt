package net.mercuryksm.rotalm

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import net.mercuryksm.rotalm.di.platformModule

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "rotalm",
    ) {
        App(platformModule = platformModule)
    }
}