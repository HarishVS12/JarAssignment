package com.harish.jarassignment.data.repo

import com.harish.jarassignment.domain.model.OnboardingModel

interface MainRepo {

    suspend fun getOnboardingData(): OnboardingModel

}