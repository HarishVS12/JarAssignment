package com.harish.jarassignment.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.harish.jarassignment.domain.model.OnboardingData
import com.harish.jarassignment.presentation.state.OnboardingAnimationStates
import kotlinx.coroutines.delay


@Composable
fun OnboardingScreenContent(
    animationState: OnboardingAnimationStates,
    onboardingData: OnboardingData?,
    onNext: (OnboardingAnimationStates) -> Unit,
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
    onNavigateToLandingPage: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(top = 20.dp)
    ) {


        OnboardingWelcomeScreen(
            title = onboardingData?.manualBuyEducationData?.introTitle ?: "",
            subtitle = onboardingData?.manualBuyEducationData?.introSubtitle ?: "",
            animDuration = 500,
            onAnimationEnd = {
                onNext(OnboardingAnimationStates.ONBOARDING_TOOLBAR)
            },
            modifier = Modifier.align(Alignment.Center)
        )


        Column {

            val toolbarVisible = when (animationState) {
                OnboardingAnimationStates.ONBOARDING_WELCOME -> false
                OnboardingAnimationStates.ONBOARDING_TOOLBAR -> true
                OnboardingAnimationStates.ONBOARDING_CONTENT -> true
                OnboardingAnimationStates.ONBOARDING_ALL -> true
                else -> false
            }
            FadeOnlyVisibility(visible = toolbarVisible) {
                LaunchedEffect(key1 = animationState) {
                    if (animationState == OnboardingAnimationStates.ONBOARDING_TOOLBAR) {
                        delay(2000)
                        onNext(OnboardingAnimationStates.ONBOARDING_CONTENT)
                    }
                }
                SimpleOnboardingToolbar(
                    title = "Onboarding",
                    onNavigateBack = onBackPress
                )
            }

            val educationalCardVisibility = when (animationState) {
                OnboardingAnimationStates.ONBOARDING_WELCOME -> false
                OnboardingAnimationStates.ONBOARDING_TOOLBAR -> false
                OnboardingAnimationStates.ONBOARDING_CONTENT -> true
                else -> true
            }
            AnimatedVisibility(
                visible = educationalCardVisibility,
                enter = fadeIn(animationSpec = tween(durationMillis = 1000)), // Added fade in for educational cards
                exit = fadeOut(animationSpec = tween(durationMillis = 1000)) // Added fade out for educational cards
            ) {
                OnboardingEducationalCards(
                    animCardList = onboardingData?.manualBuyEducationData?.educationCardList,
                    onNextAnimState = onNext
                )
            }

        }


        val buttonVisibility = when (animationState) {
            OnboardingAnimationStates.ONBOARDING_ALL -> true
            else -> false
        }
        FadeOnlyVisibility(
            visible = buttonVisibility,
            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
                .padding(bottom = 60.dp)
        ) {
            SaveButton(
                onClick = onNavigateToLandingPage,
            )
        }
    }
}

@Composable
fun FadeOnlyVisibility(
    modifier: Modifier = Modifier,
    visible: Boolean,
    duration: Int = 1500,
    content: @Composable () -> Unit
) {
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(duration)
    )

    Box(modifier = modifier.alpha(alpha)) {
        content()
    }
}
