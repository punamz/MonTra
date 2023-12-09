package com.punam.montra.src.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String, onChange: (String) -> Unit, isPassword: Boolean = false,
    errorText: String? = null,
    label: String? = null,
    icon: ImageVector? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onChange,
        label = if (label == null) null else {
            { Text(text = label) }
        },
        leadingIcon = if (icon == null) null else {
            { Icon(imageVector = icon, contentDescription = "") }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = trailingIcon,
        maxLines = 1,
        shape = RoundedCornerShape(16.dp),
        isError = errorText != null,
        supportingText = if (errorText == null) null else {
            { Text(text = errorText) }
        },
    )
}
