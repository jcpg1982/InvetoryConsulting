package pe.com.master.machines.design.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {

    const val FORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ss"
    const val FORMAT_ISO_MS = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    const val FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
    const val FORMAT_DD_MM_YYYY = "dd/MM/yyyy"
    const val FORMAT_DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm"
    const val FORMAT_HH_MM = "HH:mm"

    private val INPUT_FORMATS = listOf(
        FORMAT_ISO,
        FORMAT_ISO_MS,
        FORMAT_YYYY_MM_DD
    )

    fun formatDate(dateString: String?, outputFormat: String): String {
        if (dateString.isNullOrBlank()) return ""

        for (format in INPUT_FORMATS) {
            try {
                val inputFormatter = SimpleDateFormat(format, Locale.getDefault()).apply {
                    isLenient = false
                }
                val date = inputFormatter.parse(dateString)
                if (date != null) {
                    val outputFormatter = SimpleDateFormat(outputFormat, Locale.getDefault())
                    return outputFormatter.format(date)
                }
            } catch (e: Exception) {
                continue
            }
        }

        return dateString
    }
}
