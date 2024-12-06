package org.blakasutha.imu_tcp.feature_tcp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.blakasutha.imu_tcp.BuildConfig
import org.blakasutha.imu_tcp.core.data.onError
import org.blakasutha.imu_tcp.core.data.onSuccess
import org.blakasutha.imu_tcp.feature_tcp.data.TcpClient

class TcpViewModel(
    private val tcpClient: TcpClient,
) : ViewModel() {

    private val TAG = "TcpViewModel"

    private val _state = MutableStateFlow(TcpState())
    val state = _state.asStateFlow()

    private val _event = Channel<TcpEvent>()
    val event = _event.receiveAsFlow()

    init {
        viewModelScope.launch {

            tcpClient.connect(
                host = BuildConfig.networkHost,
                port = BuildConfig.networkPort,
            ).onSuccess {
                _event.send(TcpEvent.ShowToast("Successfully connected"))
            }.onError {
                _event.send(TcpEvent.ShowToast("Error when connecting"))
            }

        }
    }

    suspend fun sendData(data: String) {
        tcpClient.send(data)
            .onSuccess {
                Log.d(TAG, "sendData: success")
//                    _event.send(TcpEvent.ShowToast("Successfully sent"))
            }.onError {
                Log.d(TAG, "sendData: error")
//                    _event.send(TcpEvent.ShowToast("Error when sending"))
            }
    }

    fun onAction(action: TcpAction) {
        when (action) {
            is TcpAction.OnFirstOctetChange -> {
                setFirstOctet(action.text)
            }
            is TcpAction.OnSecondOctetChange -> {
                setSecondOctet(action.text)
            }
            is TcpAction.OnThirdOctetChange -> {
                setThirdOctet(action.text)
            }
            is TcpAction.OnForthOctetChange -> {
                setForthOctet(action.text)
            }
            is TcpAction.OnButtonClick -> {

            }
        }
    }

    private fun setFirstOctet(text: String) {
        _state.update {
            it.copy(firstOctet = text)
        }
    }

    private fun setSecondOctet(text: String) {
        _state.update {
            it.copy(secondOctet = text)
        }
    }

    private fun setThirdOctet(text: String) {
        _state.update {
            it.copy(thirdOctet = text)
        }
    }

    private fun setForthOctet(text: String) {
        _state.update {
            it.copy(forthOctet = text)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            tcpClient.disconnect()
        }
    }

}