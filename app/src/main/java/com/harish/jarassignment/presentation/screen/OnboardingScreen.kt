package com.harish.jarassignment.presentation.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.harish.jarassignment.core.util.hexToComposeColor
import com.harish.jarassignment.presentation.component.OnboardingScreenContent
import com.harish.jarassignment.presentation.state.OnboardingActions
import com.harish.jarassignment.presentation.viewmodel.OnboardingViewModel


@Composable
fun OnboardingScreenRoot(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<OnboardingViewModel>()
    OnboardingScreen(modifier, viewModel)
}


@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, viewModel: OnboardingViewModel) {

    val state by viewModel.onboardingState.collectAsState()

    val animatedStartColor by animateColorAsState(
        targetValue = state.bgStartColor.hexToComposeColor(),
        label = "colorAnimation",
        animationSpec = tween(1000)
    )
    val animatedEndColor by animateColorAsState(
        targetValue = state.bgEndColor.hexToComposeColor(),
        label = "colorAnimationEnd",
        animationSpec = tween(1000)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(animatedStartColor, animatedEndColor)))
            .padding(top = 20.dp)
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.isError -> {
                Text(text = state.errorMessage ?: "Something went wrong")
            }

            state.onboardingData != null -> {
                OnboardingScreenContent(
                    animationState = state.onboardingAnimationState,
                    onboardingData = state.onboardingData?.onboardingData,
                    onNext = { nextState ->
                        viewModel.onAction(
                            OnboardingActions.ChangeOnboardingAnimationState(nextState)
                        )
                    },
                    modifier = modifier.fillMaxSize()
                )
            }

            else -> {}
        }
    }

}




