package org.blakasutha.imu_tcp

import android.app.Application
import org.blakasutha.imu_tcp.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ImuTcpApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ImuTcpApp)
            androidLogger()

            modules(appModule)
        }
    }
}