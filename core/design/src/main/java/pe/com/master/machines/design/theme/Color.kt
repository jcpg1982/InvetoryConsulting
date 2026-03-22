package pe.com.master.machines.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ============================================================================
// 1. PALETA DE COLORES BASE (Primitivos)
// ============================================================================
val ColorWhite = Color(0xFFFFFFFF)
val ColorBlack = Color(0xFF000000)

// --- LIGHT CORE (Profesional & Corporativo) ---
private val PrimaryLight = Color(0xFF005AC1) // Azul Corporativo
private val OnPrimaryLight = ColorWhite
private val PrimaryContainerLight = Color(0xFFD8E2FF)
private val OnPrimaryContainerLight = Color(0xFF001A41)

private val SecondaryLight = Color(0xFF535F70) // Gris Azulado
private val OnSecondaryLight = ColorWhite
private val SecondaryContainerLight = Color(0xFFD7E3F7)
private val OnSecondaryContainerLight = Color(0xFF101C2B)

private val TertiaryLight = Color(0xFF6B5778) // Púrpura sobrio
private val OnTertiaryLight = ColorWhite
private val TertiaryContainerLight = Color(0xFFF2DAFF)
private val OnTertiaryContainerLight = Color(0xFF251431)

private val BackgroundLight = Color(0xFFFDFBFF)
private val SurfaceLight = Color(0xFFFDFBFF)
private val SurfaceVariantLight = Color(0xFFDFE2EB) // Optimizado para tarjetas
private val OnSurfaceLight = Color(0xFF1A1C1E) // TextPrimaryLight original
private val OnSurfaceVariantLight = Color(0xFF43474E) // TextSecondaryLight original
private val OutlineLight = Color(0xFF73777F) // BorderLight original

// --- DARK CORE (Elegante & Profundo) ---
private val PrimaryDark = Color(0xFFAEC6FF)
private val OnPrimaryDark = Color(0xFF002E69)
private val PrimaryContainerDark = Color(0xFF004494)
private val OnPrimaryContainerDark = Color(0xFFD8E2FF)

private val SecondaryDark = Color(0xFFBBC7DB)
private val OnSecondaryDark = Color(0xFF253140)
private val SecondaryContainerDark = Color(0xFF3B4758)
private val OnSecondaryContainerDark = Color(0xFFD7E3F7)

private val TertiaryDark = Color(0xFFD6BEE4)
private val OnTertiaryDark = Color(0xFF3B2948)
private val TertiaryContainerDark = Color(0xFF523F5F)
private val OnTertiaryContainerDark = Color(0xFFF2DAFF)

private val BackgroundDark = Color(0xFF1A1C1E)
private val SurfaceDark = Color(0xFF1A1C1E)
private val SurfaceVariantDark = Color(0xFF43474E)
private val OnSurfaceDark = Color(0xFFE2E2E6) // TextPrimaryDark original
private val OnSurfaceVariantDark = Color(0xFFC3C6CF) // TextSecondaryDark original
private val OutlineDark = Color(0xFF8D9199) // BorderDark original

// ============================================================================
// 2. COLORES DE ESTADO (Semánticos para Inventario)
// ============================================================================

// Éxito (Escaneo correcto)
private val SuccessGreenLight = Color(0xFF2E7D32)
private val SuccessGreenDark = Color(0xFF81C784)

// Error (Código no existe / Fallo)
private val ErrorRedLight = Color(0xFFBA1A1A)
private val OnErrorRedLight = ColorWhite
private val ErrorRedDark = Color(0xFFFFB4AB)
private val OnErrorRedDark = Color(0xFF690005)

// Advertencia (Stock Bajo / Requiere Revisión)
private val WarningOrangeLight = Color(0xFF855300)
private val OnWarningOrangeLight = ColorWhite
private val WarningOrangeDark = Color(0xFFFFB951)
private val OnWarningOrangeDark = Color(0xFF462A00)


// ============================================================================
// 3. COLOR SCHEMES (M3)
// ============================================================================

val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,
    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,
    onTertiaryContainer = OnTertiaryContainerLight,
    background = BackgroundLight,
    onBackground = OnSurfaceLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    outline = OutlineLight,
    error = ErrorRedLight,
    onError = OnErrorRedLight
    // Nota: Material 3 no incluye 'success' ni 'warning' nativamente en el Scheme
)

val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    secondary = SecondaryDark,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,
    tertiary = TertiaryDark,
    onTertiary = OnTertiaryDark,
    tertiaryContainer = TertiaryContainerDark,
    onTertiaryContainer = OnTertiaryContainerDark,
    background = BackgroundDark,
    onBackground = OnSurfaceDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    outline = OutlineDark,
    error = ErrorRedDark,
    onError = OnErrorRedDark
)

// ============================================================================
// 4. EXTENSIONES ÚTILES (Para usar en la UI)
// ============================================================================

/**
 * Uso: Text("Texto Secundario", color = MaterialTheme.colorScheme.textSecondary)
 */
val textSecondary: Color
    @Composable get() = if (isSystemInDarkTheme()) OnSurfaceVariantDark else OnSurfaceVariantLight

/**
 * Uso: Icon(..., tint = MaterialTheme.colorScheme.success)
 * Ideal para el flash de confirmación del escáner.
 */
val success: Color
    @Composable get() = if (isSystemInDarkTheme()) SuccessGreenDark else SuccessGreenLight

/**
 * Uso: Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.warningContainer))
 * Ideal para resaltar productos con stock bajo.
 */
val warning: Color
    @Composable get() = if (isSystemInDarkTheme()) WarningOrangeDark else WarningOrangeLight

val onWarning: Color
    @Composable get() = if (isSystemInDarkTheme()) OnWarningOrangeDark else OnWarningOrangeLight