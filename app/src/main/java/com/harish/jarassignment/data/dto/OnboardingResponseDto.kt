package com.harish.jarassignment.data.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnboardingResponseDto(
    @SerializedName("data")
    val onboardingData: OnboardingDataDto? = null,
    @SerializedName("success")
    val success: Boolean? = null
): Parcelable


@Parcelize
data class OnboardingDataDto(
    @SerializedName("manualBuyEducationData")
    val manualBuyEducationData: ManualBuyEducationDataDto? = null
): Parcelable


@Parcelize
data class ManualBuyEducationDataDto(
    @SerializedName("actionText")
    val actionText: String? = null,
    @SerializedName("bottomToCenterTranslationInterval")
    val bottomToCenterTranslationInterval: Int? = null,
    @SerializedName("cohort")
    val cohort: String? = null,
    @SerializedName("collapseCardTiltInterval")
    val collapseCardTiltInterval: Int? = null,
    @SerializedName("collapseExpandIntroInterval")
    val collapseExpandIntroInterval: Int? = null,
    @SerializedName("combination")
    val combination: String? = null,
    @SerializedName("ctaLottie")
    val ctaLottie: String? = null,
    @SerializedName("educationCardList")
    val educationCardList: List<EducationCardDto?>? = null,
    @SerializedName("expandCardStayInterval")
    val expandCardStayInterval: Int? = null,
    @SerializedName("introSubtitle")
    val introSubtitle: String? = null,
    @SerializedName("introSubtitleIcon")
    val introSubtitleIcon: String? = null,
    @SerializedName("introTitle")
    val introTitle: String? = null,
    @SerializedName("saveButtonCta")
    val saveButtonCta: SaveButtonCtaDto? = null,
    @SerializedName("screenType")
    val screenType: String? = null,
    @SerializedName("seenCount")
    val seenCount: String? = null,
    @SerializedName("shouldShowBeforeNavigating")
    val shouldShowBeforeNavigating: Boolean? = null,
    @SerializedName("shouldShowOnLandingPage")
    val shouldShowOnLandingPage: Boolean? = null,
    @SerializedName("toolBarIcon")
    val toolBarIcon: String? = null,
    @SerializedName("toolBarText")
    val toolBarText: String? = null
): Parcelable


@Parcelize
data class SaveButtonCtaDto(
    @SerializedName("backgroundColor")
    val backgroundColor: String? = null,
    @SerializedName("deeplink")
    val deeplink: String? = null,
    @SerializedName("icon")
    val icon: String? = null,
    @SerializedName("order")
    val order: String? = null,
    @SerializedName("strokeColor")
    val strokeColor: String? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("textColor")
    val textColor: String? = null
): Parcelable


@Parcelize
data class EducationCardDto(
    @SerializedName("backGroundColor")
    val backGroundColor: String? = null,
    @SerializedName("collapsedStateText")
    val collapsedStateText: String? = null,
    @SerializedName("endGradient")
    val endGradient: String? = null,
    @SerializedName("expandStateText")
    val expandStateText: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("startGradient")
    val startGradient: String? = null,
    @SerializedName("strokeEndColor")
    val strokeEndColor: String? = null,
    @SerializedName("strokeStartColor")
    val strokeStartColor: String? = null
): Parcelable