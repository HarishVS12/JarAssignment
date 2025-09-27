package com.harish.jarassignment.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.harish.jarassignment.domain.model.EducationCard


@Composable
fun OnboardingEducationalCards(animCardList: List<EducationCard?>?) {
    var visibleCards by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        animCardList?.take(visibleCards)?.forEachIndexed { index, educationCard ->
            AnimateSequentialCards(
                expandedText = educationCard?.expandStateText ?: "",
                expandedImage = educationCard?.image ?: "",
                collapsedText = educationCard?.collapsedStateText ?: "",
                collapsedImage = educationCard?.image ?: "",
                index = index,
                isLastCard = index == animCardList.lastIndex,
                onCollapsed = {
                    if (visibleCards < animCardList.size) {
                        visibleCards++
                    }
                },
                finalOffsetY = index.times(110.dp)
            )
        }
    }

    LaunchedEffect(Unit) {
        visibleCards = 1
    }
}

