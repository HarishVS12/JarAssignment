package com.harish.jarassignment.domain.usecase

import com.harish.jarassignment.core.util.DomainError
import com.harish.jarassignment.core.util.DomainException
import com.harish.jarassignment.core.util.ResultMain
import com.harish.jarassignment.data.repo.MainRepo
import com.harish.jarassignment.domain.model.OnboardingModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOnboardingUseCase @Inject constructor(
    private val mainRepo: MainRepo
) {

    suspend operator fun invoke(): Flow<ResultMain<OnboardingModel>> = flow {
        emit(ResultMain.Loading)
        try {
            val onboardingData = mainRepo.getOnboardingData()
            emit(ResultMain.Success(onboardingData))
        } catch (e: DomainException) {
            val message = when (e.error) {
                is DomainError.Network -> "Network error, please try again"
                is DomainError.Timeout -> "Request timed out"
                is DomainError.NotFound -> "User not found"
                is DomainError.Unauthorized -> "Unauthorized access"
                is DomainError.Unknown -> "Unexpected error: ${e.error.cause}"
            }
            emit(ResultMain.Error(message))
        }
    }
}