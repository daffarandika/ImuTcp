package org.blakasutha.imu_tcp.feature_tcp.presentation

data class TcpState(
    val firstOctet: String = "192",
    val secondOctet: String = "168",
    val thirdOctet: String = "11",
    val forthOctet: String = "531",
    val port: String = "6969",
    val isConnected: Boolean = false
)
