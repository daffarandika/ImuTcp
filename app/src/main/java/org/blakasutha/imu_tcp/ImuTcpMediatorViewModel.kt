package org.blakasutha.imu_tcp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.blakasutha.imu_tcp.feature_imu.presentation.ImuViewModel
import org.blakasutha.imu_tcp.feature_tcp.presentation.TcpAction
import org.blakasutha.imu_tcp.feature_tcp.presentation.TcpViewModel

@OptIn(FlowPreview::class)
class ImuTcpMediatorViewModel(
    imuViewModel: ImuViewModel,
    private val tcpViewModel: TcpViewModel
): ViewModel() {

    val imuState = imuViewModel.state
    val tcpState = tcpViewModel.state
    val tcpEvent = tcpViewModel.event

    fun onAction(action: TcpAction) {
        tcpViewModel.onAction(action)
    }

    init {
        viewModelScope.launch (Dispatchers.IO) {
            imuState
                .sample(100L)
                .collect { imuState ->
                    Log.d("MediatorViewModel", "sending from mediator: $imuState")
                    tcpViewModel.sendData(Json.encodeToString(imuState))
//                    tcpViewModel.sendData(imuState.toString())
                }
        }
    }
}