package pe.com.master.machines.consulta_inventario.di

import android.app.Application
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import pe.com.master.machines.common.ConstantsSystemProperties.fileProvider
import pe.com.master.machines.common.ConstantsSystemProperties.versionCodeDevice
import pe.com.master.machines.common.ConstantsSystemProperties.versionNameDevice
import pe.com.master.machines.common.ConstantsSystemProperties.versionReleaseDevice
import pe.com.master.machines.consulta_inventario.BuildConfig

@HiltAndroidApp
class ConsultaInventarioApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //FirebaseApp.initializeApp(this)
        saveDataSystem()
    }

    fun saveDataSystem() {
        versionCodeDevice = BuildConfig.VERSION_CODE
        versionNameDevice = BuildConfig.VERSION_NAME
        versionReleaseDevice = Build.VERSION.RELEASE
        fileProvider = "${this.packageName}.provider"
    }

}