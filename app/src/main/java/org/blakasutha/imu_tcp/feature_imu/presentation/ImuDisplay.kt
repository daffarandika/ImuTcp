package org.blakasutha.imu_tcp.feature_imu.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.blakasutha.imu_tcp.ui.theme.ImuTcpTheme
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun ImuDisplay(
    state: ImuState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Gyroscope",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "x: ${state.gyroX.format()}",
            fontSize = 18.sp
        )
        Text(
            text = "y: ${state.gyroY.format()}",
            fontSize = 18.sp
        )
        Text(
            text = "z: ${state.gyroZ.format()}",
            fontSize = 18.sp
        )
        Text(
            text = "Accelerometer",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "x: ${state.accelX.format()}",
            fontSize = 18.sp
        )
        Text(
            text = "y: ${state.accelY.format()}",
            fontSize = 18.sp
        )
        Text(
            text = "z: ${state.accelZ.format()}",
            fontSize = 18.sp
        )
        Text(
            text = "Rotation",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "x: ${state.rotationX.format()}",
            fontSize = 18.sp
        )
        Text(
            text = "y: ${state.rotationY.format()}",
            fontSize = 18.sp
        )
        Text(
            text = "z: ${state.rotationZ.format()}",
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
private fun GyroscopeScreenPrev() {
    ImuTcpTheme {
        ImuDisplay(
            state = ImuState(0.21414f, 2414f, -0.24132f),
            modifier = Modifier.background(Color.White)
        )
    }
}

fun Float.format(): String {
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this)
}
