package com.example.ui_library.Components

import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
@Composable
fun CustomTextField(
    label: String? = null,
    value: String,
    placeholder: String,
    onChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit = {},
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    focusedIndicatorColor: Color = Color.Blue,
    unfocusedIndicatorColor: Color = Color.Gray,
    disabledIndicatorColor: Color = Color.LightGray,
    errorIndicatorColor: Color = Color.Red,
    textColor: Color = Color.Black,
    cursorColor: Color = Color.Black,
    focusedLabelColor: Color = Color.Blue,
    unfocusedLabelColor: Color = Color.Gray,
    disabledLabelColor: Color = Color.LightGray,
    focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor: Color = MaterialTheme.colorScheme.primary,
    unfocusedContainerColor: Color = Color.Transparent,
    focusedContainerColor: Color = Color.Transparent,
    focusedPlaceholderColor: Color = MaterialTheme.colorScheme.primary,
    unfocusedPlaceholderColor: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = RoundedCornerShape(4.dp),
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = 1
) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    val isPasswordType = keyboardOptions.keyboardType == KeyboardType.Password
    val visualTransformation = if (isPasswordType && !passwordVisibility) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    // Define trailingIcon composable outside of the if block
    val trailingIconContent: @Composable (() -> Unit)? = if (isPasswordType) {
        {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = if (passwordVisibility) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    },
                    contentDescription = null
                )
            }
        }
    } else {
        trailingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null
                )
            }
        }
    }
    val focusModifier = Modifier.onFocusChanged { focusState ->
        onFocusChanged(focusState)
    }
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier.then(focusModifier),
        label = label?.let { { Text(it) } },
        placeholder = { Text(placeholder) },
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null
                )
            }
        },
        trailingIcon = trailingIconContent,
        readOnly = readOnly,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportText,
        isError = isError,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        colors = OutlinedTextFieldDefaults.colors(
            focusedLabelColor = focusedLabelColor,
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
            unfocusedContainerColor = unfocusedContainerColor,
            focusedContainerColor = focusedContainerColor,
            focusedPlaceholderColor = focusedPlaceholderColor,
            unfocusedPlaceholderColor = unfocusedPlaceholderColor
        ),
        shape = shape
    )
}
