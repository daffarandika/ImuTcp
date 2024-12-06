package org.blakasutha.imu_tcp.core.data

interface Error

enum class NetworkError: Error {
    NO_INTERNET,
    SERIALIZATION,
    INVALID_CONNECTION,
    CONNECTION_CLOSED
}