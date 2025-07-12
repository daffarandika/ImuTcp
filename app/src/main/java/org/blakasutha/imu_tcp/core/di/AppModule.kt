package org.blakasutha.imu_tcp.core.di

import org.blakasutha.imu_tcp.ImuTcpMediatorViewModel
import org.blakasutha.imu_tcp.feature_imu.data.Accelerometer
import org.blakasutha.imu_tcp.feature_imu.data.Gyroscope
import org.blakasutha.imu_tcp.feature_imu.data.RotationVector
import org.blakasutha.imu_tcp.feature_imu.presentation.ImuViewModel
import org.blakasutha.imu_tcp.feature_tcp.data.TcpClient
import org.blakasutha.imu_tcp.feature_tcp.presentation.TcpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { Gyroscope(context = androidContext()) }.bind<Gyroscope>()
    single { Accelerometer(context = androidContext()) }.bind<Accelerometer>()
    single { RotationVector(context = androidContext()) }.bind<RotationVector>()
    viewModel { ImuViewModel(get(), get(), get()) }
    viewModel { TcpViewModel(TcpClient()) }
    viewModel { ImuTcpMediatorViewModel(get(), get()) }
}
