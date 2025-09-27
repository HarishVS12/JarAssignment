package com.harish.jarassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harish.jarassignment.core.util.ResultMain
import com.harish.jarassignment.domain.usecase.GetOnboardingUseCase
import com.harish.jarassignment.presentation.state.OnboardingActions
import com.harish.jarassignment.presentation.state.OnboardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getOnboardingUseCase: GetOnboardingUseCase
) : ViewModel() {

    private val _onboardingState = MutableStateFlow(OnboardingState())
    val onboardingState = _onboardingState.asStateFlow()


    init {
        onAction(OnboardingActions.GetOnboardingData)
    }

    fun onAction(action: OnboardingActions) {
        when (action) {
            OnboardingActions.GetOnboardingData -> {
                viewModelScope.launch {
                    getOnboardingData().collect { res ->
                        _onboardingState.value = when (res) {
                            is ResultMain.Loading -> OnboardingState(isLoading = true)
                            is ResultMain.Success -> OnboardingState(
                                onboardingData = res.data,
                                isError = false
                            )

                            is ResultMain.Error -> OnboardingState(
                                errorMessage = res.errorMessage,
                                isError = true
                            )
                        }
                    }
                }
            }

            is OnboardingActions.ChangeOnboardingBackgroundColor -> {
                _onboardingState.value = _onboardingState.value.copy(
                    bgStartColor = action.startGradient,
                    bgEndColor = action.endGradient
                )
            }

            is OnboardingActions.ChangeOnboardingAnimationState -> {
                _onboardingState.value = _onboardingState.value.copy(
                    onboardingAnimationState = action.state
                )
            }
        }
    }


    suspend fun getOnboardingData() = getOnboardingUseCase()
}