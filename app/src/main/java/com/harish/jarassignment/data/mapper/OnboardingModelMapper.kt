package com.harish.jarassignment.data.mapper

import com.harish.jarassignment.data.dto.*
import com.harish.jarassignment.domain.model.*

fun OnboardingResponseDto.toDomain(): OnboardingModel {
    return OnboardingModel(
        onboardingData = onboardingData?.toDomain(),
        success = success
    )
}

fun OnboardingDataDto.toDomain(): OnboardingData {
    return OnboardingData(
        manualBuyEducationData = manualBuyEducationData?.toDomain()
    )
}


fun ManualBuyEducationDataDto.toDomain(): ManualBuyEducationData {
    return ManualBuyEducationData(
        actionText = actionText,
        bottomToCenterTranslationInterval = bottomToCenterTranslationInterval,
        cohort = cohort,
        collapseCardTiltInterval = collapseCardTiltInterval,
        collapseExpandIntroInterval = collapseExpandIntroInterval,
        combination = combination,
        ctaLottie = ctaLottie,
        educationCardList = educationCardList?.map { it?.toDomain() },
        expandCardStayInterval = expandCardStayInterval,
        introSubtitle = introSubtitle,
        introSubtitleIcon = introSubtitleIcon,
        introTitle = introTitle,
        saveButtonCta = saveButtonCta?.toDomain(),
        screenType = screenType,
        seenCount = seenCount,
        shouldShowBeforeNavigating = shouldShowBeforeNavigating,
        shouldShowOnLandingPage = shouldShowOnLandingPage,
        toolBarIcon = toolBarIcon,
        toolBarText = toolBarText
    )
}

fun SaveButtonCtaDto.toDomain(): SaveButtonCta {
    return SaveButtonCta(
        backgroundColor = backgroundColor,
        deeplink = deeplink,
        icon = icon,
        order = order,
        strokeColor = strokeColor,
        text = text,
        textColor = textColor
    )
}

fun EducationCardDto.toDomain(): EducationCard {
    return EducationCard(
        backGroundColor = backGroundColor,
        collapsedStateText = collapsedStateText,
        endGradient = endGradient,
        expandStateText = expandStateText,
        image = image,
        startGradient = startGradient,
        strokeEndColor = strokeEndColor,
        strokeStartColor = strokeStartColor
    )
}
