package org.blakasutha.imu_tcp.feature_imu.data

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor

class RotationVector(
    context: Context
) :HardwareSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_GYROSCOPE,
    sensorType = Sensor.TYPE_ROTATION_VECTOR
)