package com.harish.jarassignment.core


import LandingScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.harish.jarassignment.core.navigation.Screen
import com.harish.jarassignment.core.ui.theme.JarAssignmentTheme
import com.harish.jarassignment.presentation.screen.OnboardingScreenRoot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JarAssignmentTheme {
                val navController = rememberNavController()
                val animationDuration = 500

                NavHost(
                    navController = navController,
                    startDestination = Screen.OnboardingScreen.route
                ) {
                    composable(
                        route = Screen.OnboardingScreen.route,
                        exitTransition = {
                            when (targetState.destination.route) {
                                Screen.LandingScreen.route ->
                                    slideOutOfContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                        animationSpec = tween(animationDuration)
                                    )
                                else -> null
                            }
                        },
                        popEnterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Down,
                                animationSpec = tween(animationDuration)
                            )
                        }
                    ) {
                        OnboardingScreenRoot(
                            onBackPress = {
                                finish()
                            },
                            onNavigateToLandingPage = {
                                navController.navigate(Screen.LandingScreen.route)
                            }
                        )
                    }

                    composable(
                        route = Screen.LandingScreen.route,
                    ) {
                        LandingScreen(
                            onNavigateBack = {
                                finish()
                            }
                        )
                    }
                }
            }
        }
    }
}
