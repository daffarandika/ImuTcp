package org.blakasutha.imu_tcp.feature_tcp.presentation

data class TcpState(
    val firstOctet: String = "192",
    val secondOctet: String = "168",
    val thirdOctet: String = "18",
    val forthOctet: String = "155",
    val port: String = "6969",
    val isConnected: Boolean = false
) {
    fun getIp(): String {
        return "$firstOctet.$secondOctet.$thirdOctet.$forthOctet"
    }
}