package pe.com.master.machines.design.components.custom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import pe.com.master.machines.design.components.text.CustomText
import java.util.Locale

@Composable
fun TwoMinuteTimer(
    modifier: Modifier = Modifier,
    initialSeconds: Int = 120,
    onTimerFinish: () -> Unit = {}
) {
    var secondsLeft by remember { mutableIntStateOf(initialSeconds) }

    LaunchedEffect(key1 = secondsLeft) {
        if (secondsLeft > 0) {
            delay(1000L)
            secondsLeft -= 1
        } else {
            onTimerFinish()
        }
    }

    val minutes = secondsLeft / 60
    val seconds = secondsLeft % 60
    val timeString = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    if (secondsLeft <= 10) {
        CustomText(
            text = timeString,
            modifier = modifier,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}