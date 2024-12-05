package org.blakasutha.imu_tcp.feature_imu.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.blakasutha.imu_tcp.feature_imu.data.Accelerometer
import org.blakasutha.imu_tcp.feature_imu.data.Gyroscope

class ImuViewModel(
    gyroscope: Gyroscope,
    accelerometer: Accelerometer
): ViewModel() {
    private val _state = MutableStateFlow(ImuState())
    val state = _state.asStateFlow()

    init {
        gyroscope.startListening()
        gyroscope.setOnSensorValuesChangedListener(::handleRotation)

        accelerometer.startListening()
        accelerometer.setOnSensorValuesChangedListener(::handleMovement)
    }

    private fun handleRotation(values: List<Float>) {
        _state.update {
            it.copy(
                gyroX = values[0],
                gyroY = values[1],
                gyroZ = values[2],
            )
        }
    }

    private fun handleMovement(values: List<Float>) {
        _state.update {
            it.copy(
                accelX = values[0],
                accelY = values[1],
                accelZ = values[2],
            )
        }
    }
}