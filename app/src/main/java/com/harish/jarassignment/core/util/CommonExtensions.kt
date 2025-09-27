package com.harish.jarassignment.core.util

import androidx.compose.ui.graphics.Color

fun String.hexToComposeColor(): Color {
    val cleanHexString = this.removePrefix("#")

    val argbHexString = if (cleanHexString.length == 6) {
        "FF$cleanHexString"
    } else {
        cleanHexString
    }
    return Color(argbHexString.toLong(16))
}