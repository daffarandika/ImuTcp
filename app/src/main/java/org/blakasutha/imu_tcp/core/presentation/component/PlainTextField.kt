package org.blakasutha.imu_tcp.core.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.blakasutha.imu_tcp.ui.theme.ImuTcpTheme

@Composable
fun PlainTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: @Composable () -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodySmall,
        visualTransformation = VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors().copy(
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(56.dp)
    )}

@Preview
@Composable
private fun PlainTextFieldPrev() {
    ImuTcpTheme {
        PlainTextField(value = "hai", onValueChange = {})
    }
}