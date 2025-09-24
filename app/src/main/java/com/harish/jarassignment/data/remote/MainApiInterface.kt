package com.harish.jarassignment.data.remote

import com.harish.jarassignment.data.dto.OnboardingResponseDto
import retrofit2.http.GET

interface MainApiInterface {

    companion object Companion {
        const val GET_ONBOARDING_DATA = "/_assets/shared/education-metadata.json"
    }

    @GET(GET_ONBOARDING_DATA)
    suspend fun getOnboardingData(): OnboardingResponseDto

}