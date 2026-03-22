package pe.com.master.machines.design.components.images

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun CustomImage(
    model: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    viewShimmer: Boolean = true,
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    contentScale: ContentScale = ContentScale.Fit,
    isChangeColorIcon: Boolean = false,
    colorIcon: Color = Color.Unspecified,
    alpha: Float = DefaultAlpha,
    errorModel: Any? = null
) {
    val context = LocalContext.current
    val density = LocalDensity.current.density
    var widthInDp by remember { mutableFloatStateOf(0f) }
    var heightInDp by remember { mutableFloatStateOf(0f) }

    val imageRequest = remember(model) {
        ImageRequest.Builder(context)
            .data(model)
            .crossfade(true)
            .build()
    }

    SubcomposeAsyncImage(
        model = imageRequest,
        contentDescription = contentDescription,
        modifier = modifier.onGloballyPositioned { coordinates ->
            widthInDp = coordinates.size.width.toFloat() / density
            heightInDp = coordinates.size.height.toFloat() / density
        },
        alpha = alpha,
        contentScale = contentScale,
        loading = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (viewShimmer) {
                    AnimatedShimmer(modifier = Modifier.fillMaxSize())
                } else {
                    val indicatorSize = (minOf(widthInDp, heightInDp) * 0.5f).coerceIn(24f, 48f).dp
                    CircularProgressIndicator(
                        modifier = Modifier.size(indicatorSize),
                        color = primaryColor,
                        strokeWidth = 2.dp
                    )
                }
            }
        },
        error = {
            if (errorModel != null) {
                SubcomposeAsyncImage(
                    model = errorModel,
                    contentDescription = "Error Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        },
        success = {
            if (isChangeColorIcon) {
                Box(modifier = Modifier.fillMaxSize()) {
                    this@SubcomposeAsyncImage.SubcomposeAsyncImageContent(
                        modifier = Modifier.align(Alignment.Center),
                        colorFilter = ColorFilter.tint(colorIcon)
                    )
                }
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    )
}

@Composable
fun AnimatedShimmer(
    modifier: Modifier = Modifier
) {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Box(modifier = modifier.background(brush))
}
