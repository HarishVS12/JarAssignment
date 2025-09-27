package com.harish.jarassignment.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.harish.jarassignment.core.util.hexToComposeColor
import com.harish.jarassignment.domain.model.SaveButtonCta

@Composable
fun SaveButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color = Color(0xFF2A283D),
    contentColor: Color = Color(0xFFE0E0E0),
    textStyle: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    iconSize: Int = 20,
    saveButtonCta: SaveButtonCta?,
    lottieUrl: String,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val composition by rememberLottieComposition(LottieCompositionSpec.Url(lottieUrl))
    val progress by animateLottieCompositionAsState(composition, restartOnPlay = false, iterations = Int.MAX_VALUE)

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background((saveButtonCta?.backgroundColor?.hexToComposeColor()) ?: backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(PaddingValues(horizontal = 30.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = saveButtonCta?.text ?: "",
            color = contentColor,
            style = textStyle
        )
        Spacer(modifier = Modifier.width(8.dp))
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(60.dp).rotate(180f),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SaveButtonPreview() {
//    SaveButton(onClick = {})
}
