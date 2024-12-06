package org.blakasutha.imu_tcp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.blakasutha.imu_tcp.feature_imu.presentation.ImuDisplay
import org.blakasutha.imu_tcp.feature_imu.presentation.ImuState
import org.blakasutha.imu_tcp.feature_imu.presentation.ImuViewModel
import org.blakasutha.imu_tcp.feature_tcp.presentation.TcpState
import org.blakasutha.imu_tcp.feature_tcp.presentation.TcpViewModel
import org.blakasutha.imu_tcp.ui.theme.IMUTCPTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMUTCPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val imuViewModel = getViewModel<ImuViewModel>()
                    val imuState by imuViewModel.state.collectAsState()
                    val tcpViewModel = getViewModel<TcpViewModel>()
                    val tcpState by tcpViewModel.state.collectAsState()
                    AppScreen(
                        tcpState = tcpState,
                        imuState = imuState,
                        modifier = Modifier.padding(innerPadding),
                        onAction = { tcpViewModel.onAction(it) }
                    )
                }
            }
        }
    }
}