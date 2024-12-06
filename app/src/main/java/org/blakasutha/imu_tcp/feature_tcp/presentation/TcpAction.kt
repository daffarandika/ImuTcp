package org.blakasutha.imu_tcp.feature_tcp.presentation

sealed interface TcpAction {
    data class OnFirstOctetChange(val text: String): TcpAction
    data class OnSecondOctetChange(val text: String): TcpAction
    data class OnThirdOctetChange(val text: String): TcpAction
    data class OnForthOctetChange(val text: String): TcpAction
    data object OnButtonClick: TcpAction
}