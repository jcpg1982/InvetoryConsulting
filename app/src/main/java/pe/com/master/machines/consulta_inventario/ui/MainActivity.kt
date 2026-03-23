package pe.com.master.machines.consulta_inventario.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.root_navigation.ui.RootNavigationWrapper

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var keepSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplashScreen }
        Handler(Looper.getMainLooper()).postDelayed({ keepSplashScreen = false }, 1000L)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val iconView = splashScreenView.iconView
            iconView.pivotX = iconView.width / 2f
            iconView.pivotY = iconView.height / 2f

            val scaleX = ObjectAnimator.ofFloat(iconView, View.SCALE_X, 1f, 0.3f, 2f)
            val scaleY = ObjectAnimator.ofFloat(iconView, View.SCALE_Y, 1f, 0.3f, 2f)
            val alpha = ObjectAnimator.ofFloat(splashScreenView.view, View.ALPHA, 1f, 0f)

            val animatorSet = AnimatorSet().apply {
                duration = 600L
                interpolator = AnticipateInterpolator()
                playTogether(scaleX, scaleY, alpha)
            }

            animatorSet.doOnEnd {
                splashScreenView.remove()
            }

            animatorSet.start()
        }

        setContent {
            ConsultaInventarioTheme {
                RootNavigationWrapper()
            }
        }
    }
}
