package com.example.ui_library

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui_library.Components.CustomTextField
import com.example.ui_library.ui.theme.UI_libraryTheme
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ui_library.utils.validateEmail
import com.example.ui_library.utils.validatePassword

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UI_libraryTheme {
                MainContent()
            }
        }
    }
}


@Composable
fun MainContent() {

// Email Input
    var email by rememberSaveable() { mutableStateOf("") }
    var errorEmail by rememberSaveable() { mutableStateOf("") }
    var isEmailValid by rememberSaveable() { mutableStateOf(true) }
    var firstUnFocusedEmail by rememberSaveable() { mutableStateOf(false) }

    // Password Input

    var password by rememberSaveable() { mutableStateOf("") }
    var errorPassword by rememberSaveable() { mutableStateOf("") }
    var isPasswordValid by rememberSaveable() { mutableStateOf(true) }
    var firstUnFocusedPassword by rememberSaveable() { mutableStateOf(false) }

    // Phone Input
    var phone by rememberSaveable() { mutableStateOf("") }

    // Search Input
    var search by rememberSaveable() { mutableStateOf("") }


    LaunchedEffect(email, firstUnFocusedEmail) {
        isEmailValid = validateEmail(email)
        errorEmail = when {
            !isEmailValid && email.isNotEmpty() -> "Invalid email"
            firstUnFocusedEmail && email.isEmpty() -> "Required field"
            else -> ""
        }
    }

    LaunchedEffect(password, firstUnFocusedPassword) {
        if (password.isNotEmpty()) {
             val (isValid, err) = validatePassword(password)
            isPasswordValid = isValid
            errorPassword = err
        }
        if (password.isEmpty() && firstUnFocusedPassword) errorPassword = "Required field"
    }






    Column (
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

    CustomTextField(
        value = email,
        placeholder = "Email",
        onChange = {  email = it },
        onFocusChanged = { focusState ->
            Log.d("State fc ", focusState.toString())
            if (focusState.isFocused) {
                firstUnFocusedEmail = true
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        shape = RoundedCornerShape(50.dp),
        supportText = {if (!isEmailValid) Text(errorEmail) else null},
        isError = errorEmail.isNotEmpty(),

    )


    CustomTextField(
        value = password,
        placeholder = "Password",
        onChange = {  password = it },
        onFocusChanged = { focusState ->
            if (focusState.isFocused) {
                firstUnFocusedPassword = true
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(50.dp),
        supportText = {if (!isPasswordValid) Text(errorPassword) else null} ,
        isError = errorPassword.isNotEmpty(),
    )
        Log.d("password", errorPassword)
        Log.d("empty", errorPassword.isNotEmpty().toString())
        Row {

        CustomTextField(
            value = phone,
            placeholder = "2531565516",
            onChange = {  phone = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = RoundedCornerShape(50.dp)
        )

        }
        CustomTextField(
            value = search,
            placeholder = "Search",
            onChange = { search = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(50.dp),
            leadingIcon = Icons.Filled.Search,

            )
}

}