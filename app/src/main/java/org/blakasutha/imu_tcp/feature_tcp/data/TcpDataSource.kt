package org.blakasutha.imu_tcp.feature_tcp.data

import org.blakasutha.imu_tcp.core.data.NetworkError
import org.blakasutha.imu_tcp.core.data.Result

interface TcpDataSource {
    suspend fun connect(host: String, port: String): Result<Unit, NetworkError>
    suspend fun send(data: String): Result<Unit, NetworkError>
    suspend fun receive(): Result<String, NetworkError>
    suspend fun disconnect(): Result<Unit, NetworkError>
}