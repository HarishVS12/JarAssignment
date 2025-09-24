package com.harish.jarassignment.presentation.screen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.harish.jarassignment.presentation.viewmodel.OnboardingViewModel

@Composable
fun OnboardingScreenRoot(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<OnboardingViewModel>()
    OnboardingScreen(modifier, viewModel)
}


@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, viewModel: OnboardingViewModel) {
    val state by viewModel.onboardingState.collectAsState()

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }

        state.isError -> {
            Text(text = state.errorMessage ?: "Something went wrong")
        }

        state.onboardingData != null -> {
            //Text with filled data write this code below
            Text(text = state.onboardingData.toString())
        }

        else -> {}
    }

}