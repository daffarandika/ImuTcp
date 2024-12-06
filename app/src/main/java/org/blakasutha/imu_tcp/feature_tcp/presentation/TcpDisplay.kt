package org.blakasutha.imu_tcp.feature_tcp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.blakasutha.imu_tcp.ui.theme.ImuTcpTheme

@Composable
fun TcpDisplay(
    state: TcpState,
    modifier: Modifier = Modifier,
    onAction: (TcpAction) -> Unit,
){
    Column(
        modifier = modifier
            .padding(vertical = 2.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = state.firstOctet,
                onValueChange = {
                    onAction(TcpAction.OnFirstOctetChange(it))
                },
                enabled = !state.isConnected,
                modifier = Modifier.width(61.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.background,
                    focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                )
            )
            Text(
                text = ".",
            )
            OutlinedTextField(
                value = state.secondOctet,
                onValueChange = {
                    onAction(TcpAction.OnSecondOctetChange(it))
                },
                enabled = !state.isConnected,
                modifier = Modifier.width(61.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.background,
                    focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                )
            )
            Text(
                text = ".",
            )
            OutlinedTextField(
                value = state.thirdOctet,
                onValueChange = {
                    onAction(TcpAction.OnThirdOctetChange(it))
                },
                enabled = !state.isConnected,
                modifier = Modifier.width(61.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.background,
                    focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                ),
            )
            Text(
                text = ".",
            )
            OutlinedTextField(
                value = state.forthOctet,
                onValueChange = {
                    onAction(TcpAction.OnThirdOctetChange(it))
                },
                modifier = Modifier.width(61.dp),
                enabled = !state.isConnected,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.background,
                    focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                )
            )
            Text(
                text = ":"
            )
            OutlinedTextField(
                value = state.port,
                onValueChange = {},
                modifier = Modifier.width(76.dp),
                enabled = !state.isConnected,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.background,
                    focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                )
            )

        }
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            onClick = {onAction(TcpAction.OnButtonClick)},
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = MaterialTheme.colorScheme.onBackground
            ),
        ) {
            if (state.isConnected) {
                Text(text = "Disconnect")
            } else {
                Text(text = "Connect")
            }
        }
    }
}

@Preview
@Composable
private fun TcpDisplayPrev() {
    ImuTcpTheme {
        TcpDisplay(
            state = TcpState(),
            modifier = Modifier.background(Color.White),
            onAction = {}
        )
    }
}