package org.blakasutha.imu_tcp.core.di

import org.blakasutha.imu_tcp.feature_imu.data.Accelerometer
import org.blakasutha.imu_tcp.feature_imu.data.Gyroscope
import org.blakasutha.imu_tcp.feature_imu.presentation.ImuViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { Gyroscope(context = androidContext()) }.bind<Gyroscope>()
    single { Accelerometer(context = androidContext()) }.bind<Accelerometer>()
    viewModel { ImuViewModel(get(), get()) }
}
