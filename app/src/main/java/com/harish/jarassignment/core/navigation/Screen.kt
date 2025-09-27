package com.harish.jarassignment.core.navigation

sealed class Screen(val route: String) {
    object OnboardingScreen : Screen("onboardingScreenRoot")
    object LandingScreen : Screen("landingScreenRoot")
}