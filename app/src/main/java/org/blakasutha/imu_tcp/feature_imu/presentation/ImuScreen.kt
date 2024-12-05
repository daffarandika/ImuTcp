package org.blakasutha.imu_tcp.feature_imu.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.blakasutha.imu_tcp.ui.theme.IMUTCPTheme
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun ImuScreen(
    state: ImuState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Gyroscope",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "x: ${state.gyroX.format()}",
            fontSize = 36.sp
        )
        Text(
            text = "y: ${state.gyroY.format()}",
            fontSize = 36.sp
        )
        Text(
            text = "z: ${state.gyroZ.format()}",
            fontSize = 36.sp
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Accelerometer",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "x: ${state.accelX.format()}",
            fontSize = 36.sp
        )
        Text(
            text = "y: ${state.accelY.format()}",
            fontSize = 36.sp
        )
        Text(
            text = "z: ${state.accelZ.format()}",
            fontSize = 36.sp
        )
    }
}

@Preview
@Composable
private fun GyroscopeScreenPrev() {
    IMUTCPTheme {
        ImuScreen(
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
