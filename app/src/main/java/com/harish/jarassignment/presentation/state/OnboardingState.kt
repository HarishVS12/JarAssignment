package com.harish.jarassignment.presentation.state

import com.harish.jarassignment.domain.model.OnboardingModel

data class OnboardingState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = "",
    val onboardingData: OnboardingModel? = null,
    val bgStartColor: String = "#201929",
    val bgEndColor: String = "#201929"
)


sealed interface OnboardingActions {

    object GetOnboardingData : OnboardingActions

    data class ChangeOnboardingBackgroundColor(val startGradient: String, val endGradient: String) :
        OnboardingActions

}
