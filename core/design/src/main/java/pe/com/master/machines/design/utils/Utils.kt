package pe.com.master.machines.design.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.DisplayMetrics
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object Utils {

    val getPlatform
        get() = "android"

    val getDeviceName
        get() = Build.MANUFACTURER ?: "Unknown"

    val getDeviceOSVersion
        get() = Build.VERSION.RELEASE ?: "Unknown"

    val getDeviceModel
        get() = Build.MODEL ?: "Unknown"

    val getDeviceLanguage: String
        get() = Locale.getDefault().language

    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            ?: "unknown"
    }

    fun getScreenWidth(context: Context): String {
        val metrics: DisplayMetrics = context.resources.displayMetrics
        return metrics.widthPixels.toString()
    }

    fun getScreenHeight(context: Context): String {
        val metrics: DisplayMetrics = context.resources.displayMetrics
        return metrics.heightPixels.toString()
    }

    fun getAppVersion(context: Context): String {
        return try {
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.PackageInfoFlags.of(0)
                )
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getPackageInfo(context.packageName, 0)
            }
            packageInfo.versionName ?: "Unknown"
        } catch (e: Exception) {
            "Unknown"
        }
    }

    fun formatBalance(amount: Double): String {
        val symbols = DecimalFormatSymbols(Locale.US).apply {
            groupingSeparator = ','
            decimalSeparator = '.'
        }
        val decimalFormat = DecimalFormat("###,###,###,##0.00", symbols)
        return decimalFormat.format(amount)
    }

    fun horizontalSlideTransition(isPop: Boolean): ContentTransform {
        val duration = 500
        val initialOffset = if (isPop) -1 else 1
        val targetOffset = if (isPop) 1 else -1
        return slideInHorizontally(
            initialOffsetX = { it * initialOffset }, animationSpec = tween(duration)
        ) togetherWith slideOutHorizontally(
            targetOffsetX = { it * targetOffset }, animationSpec = tween(duration)
        )
    }

}
