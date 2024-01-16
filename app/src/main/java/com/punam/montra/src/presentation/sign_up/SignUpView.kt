package com.punam.montra.src.presentation.sign_up

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.punam.montra.R
import com.punam.montra.src.presentation.component.CustomTextField
import com.punam.montra.src.presentation.component.Loading
import com.punam.montra.util.Routers
import com.punam.montra.util.UiText
import com.punam.montra.util.ViewState
import com.punam.montra.util.beauty
import com.punam.montra.util.toStringRes
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpView(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController,
) {
    val focusManager = LocalFocusManager.current
    val canPop = navController.previousBackStackEntry != null
    val state = viewModel.state.value
    val context = LocalContext.current

    val snackBarHostState = remember { SnackbarHostState() }
    var isLoading by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = true) {
        viewModel.viewState.collectLatest { event ->
            when (event) {
                ViewState.Loading -> isLoading = true
                is ViewState.Error -> {
                    isLoading = false
                    snackBarHostState.showSnackbar(
                        UiText.StringResource(event.error.errorCode.toStringRes())
                            .asString(context)
                    )
                }

                is ViewState.Success -> {
                    viewModel.saveLocalData(event.value)
                    isLoading = false
                    navController.navigate(Routers.Home.name) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                }

                else -> isLoading = false
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures { focusManager.clearFocus() }

        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.sign_up))
                },
                navigationIcon =
                {
                    if (canPop) IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        if (isLoading) Loading()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                modifier = Modifier
                    .padding(top = 56.dp)
                    .fillMaxWidth(),
                value = state.nameInput,
                onChange = { viewModel.onEvent(SignUpEvent.InputName(it.beauty())) },
                label = stringResource(R.string.name),
                icon = Icons.Rounded.Person,
                keyboardType = KeyboardType.Text,
                errorText = state.nameError?.asString(context),
            )
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                value = state.emailInput,
                onChange = { viewModel.onEvent(SignUpEvent.InputEmail(it.trim())) },
                label = stringResource(R.string.email),
                icon = Icons.Rounded.Mail,
                keyboardType = KeyboardType.Email,
                errorText = state.emailError?.asString(context),
            )

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                value = state.passwordInput,
                onChange = { viewModel.onEvent((SignUpEvent.InputPassword(it.trim()))) },
                errorText = state.passwordError?.asString(context),
                label = stringResource(R.string.password),
                icon = Icons.Rounded.Lock,
                keyboardType = KeyboardType.Password,
                isPassword = state.isHidePassword,
                trailingIcon = {
                    androidx.compose.material3.Icon(
                        imageVector = if (state.isHidePassword) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            viewModel.onEvent(SignUpEvent.ToggleShowPassword)
                        }
                    )
                }
            )
            Button(
                onClick = {
                    focusManager.clearFocus()
                    viewModel.onEvent(SignUpEvent.SignUp)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = stringResource(R.string.sign_up))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.already_have_account),
                    color = MaterialTheme.colorScheme.inverseSurface
                )
                TextButton(onClick = {
                    if (canPop) navController.popBackStack()
                    else navController.navigate(Routers.Login.name) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                }) {
                    Text(
                        text = stringResource(id = R.string.login),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline,
                    )
                }
            }
        }
    }

}