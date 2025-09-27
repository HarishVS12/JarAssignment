package com.harish.jarassignment.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.harish.jarassignment.domain.model.OnboardingData
import com.harish.jarassignment.presentation.state.OnboardingAnimationStates
import kotlinx.coroutines.delay


@Composable
fun OnboardingScreenContent(
    animationState: OnboardingAnimationStates,
    onboardingData: OnboardingData?,
    onNext: (OnboardingAnimationStates) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
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

            val visible = when(animationState){
                OnboardingAnimationStates.ONBOARDING_WELCOME -> false
                OnboardingAnimationStates.ONBOARDING_TOOLBAR -> true
                OnboardingAnimationStates.ONBOARDING_CONTENT -> true
                else -> false
            }

            AnimatedVisibility(
                visible = visible ,
                enter = fadeIn(tween(500))
            ) {

                LaunchedEffect(Unit) {
                    delay(2000)
                    onNext(OnboardingAnimationStates.ONBOARDING_CONTENT)
                }

                SimpleOnboardingToolbar(
                    title = "Onboarding",
                    onNavigateBack = { println("Navigate back clicked") }
                )
            }


            AnimatedVisibility(
                visible = animationState == OnboardingAnimationStates.ONBOARDING_CONTENT,
            ) {
                OnboardingEducationalCards(
                    animCardList = onboardingData?.manualBuyEducationData?.educationCardList
                )
            }
        }
    }
}
