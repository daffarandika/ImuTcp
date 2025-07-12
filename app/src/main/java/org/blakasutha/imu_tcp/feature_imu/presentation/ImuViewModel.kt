package org.blakasutha.imu_tcp.feature_imu.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.blakasutha.imu_tcp.feature_imu.data.Accelerometer
import org.blakasutha.imu_tcp.feature_imu.data.Gyroscope
import org.blakasutha.imu_tcp.feature_imu.data.RotationVector

class ImuViewModel(
    gyroscope: Gyroscope,
    accelerometer: Accelerometer,
    rotationVector: RotationVector
): ViewModel() {
    private val _state = MutableStateFlow(ImuState())
    val state = _state.asStateFlow()

    init {
        gyroscope.startListening()
        gyroscope.setOnSensorValuesChangedListener(::handleRotation)

        accelerometer.startListening()
        accelerometer.setOnSensorValuesChangedListener(::handleMovement)

        rotationVector.startListening()
        rotationVector.setOnSensorValuesChangedListener(::handleRotationVector)
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

    private fun handleRotationVector(values: List<Float>) {
        _state.update {
            it.copy(
                rotationX = values[0],
                rotationY = values[1],
                rotationZ = values[2],
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