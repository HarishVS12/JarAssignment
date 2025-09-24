package com.harish.jarassignment.presentation.state

import com.harish.jarassignment.domain.model.OnboardingModel

data class OnboardingState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = "",
    val onboardingData: OnboardingModel? = null
)


sealed interface OnboardingActions {

    object GetOnboardingData : OnboardingActions

}