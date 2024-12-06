package org.blakasutha.imu_tcp.feature_tcp.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TcpViewModel : ViewModel() {

    private val _state = MutableStateFlow(TcpState())
    val state = _state.asStateFlow()

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

}