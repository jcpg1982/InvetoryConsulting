package pe.com.master.machines.design.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.DisplayMetrics
import pe.com.master.machines.model.BankAccount
import pe.com.master.machines.model.Transaction
import pe.com.master.machines.model.request.model.App
import pe.com.master.machines.model.request.model.Device
import pe.com.master.machines.model.request.model.Profile
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.random.Random

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

    fun getDeviceInfo(context: Context) = Device(
        deviceId = getDeviceId(context),
        height = getScreenHeight(context),
        model = getDeviceModel,
        name = getDeviceName,
        platform = getPlatform,
        version = getDeviceOSVersion,
        width = getScreenWidth(context)
    )

    fun getAppInfo(context: Context) = App(
        version = getAppVersion(context)
    )

    fun getProfileInfo() = Profile(
        language = getDeviceLanguage
    )

    fun formatBalance(amount: Double): String {
        val symbols = DecimalFormatSymbols(Locale.US).apply {
            groupingSeparator = ','
            decimalSeparator = '.'
        }
        val decimalFormat = DecimalFormat("###,###,###,##0.00", symbols)
        return decimalFormat.format(amount)
    }

    fun generateRandomAccount(
        forcedType: String? = null,
        forcedSymbol: String? = null
    ): BankAccount {
        val types = listOf(
            "Ahorro Soles",
            "Corriente Dólares",
            "Ahorro Euros",
            "Sueldo Soles",
            "Inversión Dólares"
        )
        val symbols = mapOf(
            "Ahorro Soles" to "S/",
            "Corriente Dólares" to "$",
            "Ahorro Euros" to "€",
            "Sueldo Soles" to "S/",
            "Inversión Dólares" to "$"
        )

        val selectedType = forcedType ?: types.random()
        val randomAccNumber = (1..14).map { (0..9).random() }.joinToString("")

        return BankAccount(
            type = selectedType,
            currencySymbol = forcedSymbol ?: symbols[selectedType] ?: "S/",
            balance = Random.nextDouble(100.0, 50000.0),
            accountNumber = randomAccNumber
        )
    }

    fun generateFakeTransactions(start: Int, count: Int): List<Transaction> {
        val descriptions = listOf(
            "Compra Saga Falabella",
            "Depósito de nómina",
            "Pago Servicio Luz",
            "Transferencia recibida",
            "Retiro Cajero",
            "Restaurante La Mar",
            "Pago Netflix",
            "Abono intereses",
            "Yape",
            "plim"
        )

        val months = listOf(
            "Ene",
            "Feb",
            "Mar",
            "Abr",
            "May",
            "Jun",
            "Jul",
            "Ago",
            "Set",
            "Oct",
            "Nov",
            "Dic"
        )

        return (start until (start + count)).map { i ->
            val isIncome = Random.nextBoolean()
            val monthIndex = (i / 28) % months.size
            val day = 28 - (i % 28)
            val month = months[monthIndex]

            Transaction(
                description = descriptions.random(),
                amount = Random.nextDouble(10.0, 500.0),
                date = "$day $month 2025",
                isIncome = isIncome
            )
        }
    }
}
