package network.chaintech.cmpfilepickerdemo.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AlertMessageDialog(
    title: String,
    message: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onNegativeClick,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = onPositiveClick) {
                Text(positiveButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = onNegativeClick) {
                Text(negativeButtonText)
            }
        }
    )
}