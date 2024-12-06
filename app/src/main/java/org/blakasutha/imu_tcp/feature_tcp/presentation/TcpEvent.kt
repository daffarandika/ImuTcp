package org.blakasutha.imu_tcp.feature_tcp.presentation

sealed interface TcpEvent {
    data class ShowToast(val text: String): TcpEvent
}