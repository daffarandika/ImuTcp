package org.blakasutha.imu_tcp.feature_imu.presentation

import kotlinx.serialization.Serializable

@Serializable
data class ImuState(
    val gyroX: Float = 0f,
    val gyroY: Float = 0f,
    val gyroZ: Float = 0f,
    val accelX: Float = 0f,
    val accelY: Float = 0f,
    val accelZ: Float = 0f,
)
