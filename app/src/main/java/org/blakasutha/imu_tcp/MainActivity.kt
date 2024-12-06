package org.blakasutha.imu_tcp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.blakasutha.imu_tcp.core.presentation.util.ObserveEvent
import org.blakasutha.imu_tcp.feature_imu.presentation.ImuViewModel
import org.blakasutha.imu_tcp.feature_tcp.presentation.TcpEvent
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
                    val mediatorViewModel = getViewModel<ImuTcpMediatorViewModel>()
                    val imuState by mediatorViewModel.imuState.collectAsState()
                    val tcpState by mediatorViewModel.tcpState.collectAsState()
                    val context = LocalContext.current
                    ObserveEvent(events = mediatorViewModel.tcpEvent) { event ->
                        when (event) {
                            is TcpEvent.ShowToast -> {
                                Toast.makeText(context, event.text, Toast.LENGTH_SHORT).show()
                            }

                            TcpEvent.Nothing -> TODO()
                        }
                    }
                    ImuTcpScreen(
                        tcpState = tcpState,
                        imuState = imuState,
                        modifier = Modifier.padding(innerPadding),
                        onAction = { mediatorViewModel.onAction(it) }
                    )
                }
            }
        }
    }
}