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

    private fun connect() {
        viewModelScope.launch {
            val host = _state.value.getIp()
            val port = _state.value.port
            tcpClient.connect(
                host = host,
                port = port,
            ).onSuccess {
                _event.send(TcpEvent.ShowToast("Successfully connected"))
            }.onError {
                _event.send(TcpEvent.ShowToast("Error when connecting"))
            }
        }
    }

    private fun disconnect() {
        viewModelScope.launch {
            tcpClient.disconnect()
                .onSuccess {
                    _event.send(TcpEvent.ShowToast("Successfully disconnected"))
                }.onError {
                    _event.send(TcpEvent.ShowToast("Error when disconnecting"))
                }
        }
    }

    fun sendData(data: String) {
        viewModelScope.launch {
            tcpClient.send(data)
                .onSuccess {
                    Log.d(TAG, "sendData: success")
                }.onError {
                    Log.d(TAG, "sendData: error")
                }
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
                if (_state.value.isConnected) {
                    disconnect()
                    _state.update {
                        it.copy(isConnected = false)
                    }
                } else {
                    connect()
                    _state.update {
                        it.copy(isConnected = true)
                    }
                }
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