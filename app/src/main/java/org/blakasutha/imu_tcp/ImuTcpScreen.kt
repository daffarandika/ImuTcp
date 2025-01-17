package org.blakasutha.imu_tcp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.blakasutha.imu_tcp.feature_imu.presentation.ImuDisplay
import org.blakasutha.imu_tcp.feature_imu.presentation.ImuState
import org.blakasutha.imu_tcp.feature_tcp.presentation.TcpAction
import org.blakasutha.imu_tcp.feature_tcp.presentation.TcpDisplay
import org.blakasutha.imu_tcp.feature_tcp.presentation.TcpState
import org.blakasutha.imu_tcp.ui.theme.ImuTcpTheme

@Composable
fun ImuTcpScreen(
    tcpState: TcpState,
    onAction: (TcpAction) -> Unit,
    imuState: ImuState,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TcpDisplay(
            state = tcpState,
            onAction = onAction
        )
        ImuDisplay(state = imuState)
    }
}

@Preview
@Composable
private fun AppScreenPrev() {
    ImuTcpTheme {
        ImuTcpScreen(
            tcpState = TcpState(),
            imuState = ImuState(),
            modifier = Modifier.background(Color.White),
            onAction = {}
        )
    }
}