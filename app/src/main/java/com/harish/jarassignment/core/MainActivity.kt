package com.harish.jarassignment.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.harish.jarassignment.core.ui.theme.JarAssignmentTheme
import com.harish.jarassignment.presentation.screen.OnboardingScreenRoot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JarAssignmentTheme {
                OnboardingScreenRoot()
            }
        }
    }
}