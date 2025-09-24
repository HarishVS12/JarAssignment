package com.harish.jarassignment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnboardingModel(
    val onboardingData: OnboardingData? = null,
    val success: Boolean? = null
): Parcelable


@Parcelize
data class OnboardingData(
    val manualBuyEducationData: ManualBuyEducationData? = null
): Parcelable


@Parcelize
data class ManualBuyEducationData(
    val actionText: String? = null,
    val bottomToCenterTranslationInterval: Int? = null,
    val cohort: String? = null,
    val collapseCardTiltInterval: Int? = null,
    val collapseExpandIntroInterval: Int? = null,
    val combination: String? = null,
    val ctaLottie: String? = null,
    val educationCardList: List<EducationCard?>? = null,
    val expandCardStayInterval: Int? = null,
    val introSubtitle: String? = null,
    val introSubtitleIcon: String? = null,
    val introTitle: String? = null,
    val saveButtonCta: SaveButtonCta? = null,
    val screenType: String? = null,
    val seenCount: String? = null,
    val shouldShowBeforeNavigating: Boolean? = null,
    val shouldShowOnLandingPage: Boolean? = null,
    val toolBarIcon: String? = null,
    val toolBarText: String? = null
): Parcelable


@Parcelize
data class SaveButtonCta(
    val backgroundColor: String? = null,
    val deeplink: String? = null,
    val icon: String? = null,
    val order: String? = null,
    val strokeColor: String? = null,
    val text: String? = null,
    val textColor: String? = null
): Parcelable


@Parcelize
data class EducationCard(
    val backGroundColor: String? = null,
    val collapsedStateText: String? = null,
    val endGradient: String? = null,
    val expandStateText: String? = null,
    val image: String? = null,
    val startGradient: String? = null,
    val strokeEndColor: String? = null,
    val strokeStartColor: String? = null
): Parcelable