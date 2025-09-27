package com.harish.jarassignment.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.harish.jarassignment.core.util.hexToComposeColor
import com.harish.jarassignment.presentation.state.OnboardingAnimationStates
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val SLOW_DURATION_LONG = 1200
private const val SLOW_DURATION_MEDIUM = 800
private const val SLOW_DURATION_SHORT = 600

@Composable
fun AnimateSequentialCards(
    collapsedText: String,
    collapsedImage: String,
    expandedImage: String,
    expandedText: String,
    backgroundColor: String,
    startGradient: String,
    endGradient: String,
    index: Int,
    isLastCard: Boolean,
    onCollapsed: () -> Unit,
    finalOffsetY: Dp,
    onNextAnimState: (OnboardingAnimationStates) -> Unit,
    onBackgroundColorChange: (String, String) -> Unit,

    ) {
    var expanded by remember { mutableStateOf(true) }
    val transition = updateTransition(targetState = expanded, label = "cardTransition")

    val density = LocalDensity.current
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    val screenHeightPx = with(density) { screenHeightDp.toPx() }
    val finalOffsetYPx = with(density) { finalOffsetY.toPx() }

    val offsetY = remember { Animatable(screenHeightPx) }
    val rotationAnim = remember { Animatable(0f) }

    val height by transition.animateDp(
        label = "heightAnim",
        transitionSpec = {
            if (targetState) {
                spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            } else {
                tween(
                    durationMillis = SLOW_DURATION_MEDIUM,
                    easing = FastOutSlowInEasing
                )
            }
        }
    ) { isExpanded -> if (isExpanded) 500.dp else 80.dp }

    val cornerRadius by transition.animateDp(
        label = "cornerAnim",
        transitionSpec = { tween(durationMillis = SLOW_DURATION_MEDIUM) }
    ) { isExpanded ->
        if (isExpanded) 20.dp else 40.dp
    }

    LaunchedEffect(key1 = Unit) {
        if (index == 0 && !isLastCard) {
            val cardHeightPx = with(density) { 900.dp.toPx() }
            val centerTargetY = (screenHeightPx / 2) - (cardHeightPx / 2)
            offsetY.snapTo(screenHeightPx)
            offsetY.animateTo(
                targetValue = centerTargetY,
                animationSpec = tween(SLOW_DURATION_LONG, easing = FastOutSlowInEasing)
            )
            onBackgroundColorChange(startGradient, endGradient)
            delay(600)
            launch {
                val tilt = if (index % 2 == 0) -8f else 7f
                rotationAnim.animateTo(
                    tilt,
                    tween(SLOW_DURATION_MEDIUM, easing = FastOutSlowInEasing)
                )
            }
            launch {
                expanded = false
            }
            coroutineScope {
                async {
                    offsetY.animateTo(
                        targetValue = finalOffsetYPx,
                        animationSpec = tween(SLOW_DURATION_LONG, easing = FastOutSlowInEasing)
                    )
                }
                launch {
                    delay(200)
                    onCollapsed()
                }
                delay(2000)
                async {
                    delay(SLOW_DURATION_MEDIUM / 2L)
                    rotationAnim.animateTo(
                        0f,
                        tween(SLOW_DURATION_SHORT, easing = FastOutSlowInEasing)
                    )
                }
            }


        } else {
            offsetY.animateTo(
                targetValue = finalOffsetYPx + 1000,
                animationSpec = tween(
                    SLOW_DURATION_LONG,
                    easing = FastOutSlowInEasing
                )
            )
            delay(600)
            onBackgroundColorChange(startGradient, endGradient)
            if (isLastCard)
                onNextAnimState(OnboardingAnimationStates.ONBOARDING_FULL_INTENT)
            offsetY.animateTo(
                targetValue = finalOffsetYPx,
                animationSpec = tween(
                    SLOW_DURATION_LONG,
                    easing = FastOutSlowInEasing
                )
            )
            delay(800)

            if (!isLastCard) {
                launch {
                    val tilt = if (index % 2 == 0) -8f else 8f
                    rotationAnim.animateTo(
                        tilt,
                        tween(SLOW_DURATION_MEDIUM, easing = FastOutSlowInEasing)
                    )
                }
                launch {
                    expanded = false
                }
                launch {
                    delay(200)
                    onCollapsed()
                }
                delay(2000)
                rotationAnim.animateTo(0f, tween(SLOW_DURATION_SHORT, easing = FastOutSlowInEasing))
            } else {
                expanded = true
                onNextAnimState(OnboardingAnimationStates.ONBOARDING_ALL)
                onCollapsed()
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .graphicsLayer {
                translationY = offsetY.value
                rotationZ = rotationAnim.value
            }
            .padding(horizontal = 8.dp)
            .clickable {
                expanded = !expanded
            },
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor.hexToComposeColor()
        ),
    ) {
        if (expanded) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = expandedImage,
                    contentDescription = "Expanded image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Transparent)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = expandedText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = collapsedImage,
                        contentDescription = "Collapsed image",
                        modifier = Modifier.clip(RoundedCornerShape(100.dp)).size(32.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = collapsedText,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand card",
                    tint = Color.White.copy(alpha = 0.7f)
                )
            }
        }
    }
}


@Preview
@Composable
fun AnimatedCardPreview() {
    AnimateSequentialCards(
        collapsedText = "This is the collapsed text",
        collapsedImage = "",
        expandedImage = "",
        expandedText = "This is the expanded text",
        backgroundColor = "",
        index = 0,
        isLastCard = false,
        onCollapsed = {},
        finalOffsetY = 0.dp,
        startGradient = "",
        endGradient = "",
        onBackgroundColorChange = { _, _ ->
        },
        onNextAnimState = {}
    )
}

