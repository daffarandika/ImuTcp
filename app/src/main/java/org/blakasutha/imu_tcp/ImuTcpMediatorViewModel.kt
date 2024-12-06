package org.blakasutha.imu_tcp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import org.blakasutha.imu_tcp.feature_imu.presentation.ImuViewModel
import org.blakasutha.imu_tcp.feature_tcp.presentation.TcpViewModel

@OptIn(FlowPreview::class)
class ImuTcpMediatorViewModel(
    private val imuViewModel: ImuViewModel,
    private val tcpViewModel: TcpViewModel
): ViewModel() {
    init {
        viewModelScope.launch {
            imuViewModel
                .state
                .debounce(1000L)
                .collect { data ->
                    tcpViewModel.sendData(data.toString())
                }
        }
    }
}