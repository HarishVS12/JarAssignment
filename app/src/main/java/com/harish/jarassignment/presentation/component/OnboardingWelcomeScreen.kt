package com.harish.jarassignment.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.jarassignment.core.util.hexToComposeColor
import kotlinx.coroutines.delay


@Composable
fun OnboardingWelcomeScreen(
    title: String,
    subtitle: String,
    animDuration: Long,
    onAnimationEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showText by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = showText,
        modifier = modifier,
        exit = fadeOut(animationSpec = tween(durationMillis = animDuration.toInt()))
    ) {
        Column(
            modifier = modifier
                .wrapContentSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(700)
                        )
                    ) {
                        append(title)
                    }
                    append("\n")
                    withStyle(
                        style = SpanStyle(
                            color = "#F8DC83".hexToComposeColor(),
                            fontSize = 28.sp,
                            fontWeight = FontWeight(700),
                        )
                    ) {
                        append(subtitle)
                    }
                },
                lineHeight = 40.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }

    LaunchedEffect(true) {
        showText = false
        delay(animDuration)
        onAnimationEnd()
    }

}


@Preview(showBackground = true)
@Composable
fun OnboardingWelcomeScreenPreview() {
    OnboardingWelcomeScreen(
        title = "Welcome to",
        subtitle = "Onboarding",
        animDuration = 2999,
        onAnimationEnd = {}
    )
}
