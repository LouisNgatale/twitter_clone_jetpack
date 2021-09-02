package com.louisngatale.twitterclone.presentation.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.louisngatale.twitterclone.MainApplication
import com.louisngatale.twitterclone.R
import com.louisngatale.twitterclone.network.request.RegisterRequest
import com.louisngatale.twitterclone.presentation.theme.Blue200
import com.louisngatale.twitterclone.presentation.theme.TwitterCloneTheme
import com.louisngatale.twitterclone.presentation.theme.TwitterShapes
import com.louisngatale.twitterclone.presentation.theme.White
import com.louisngatale.twitterclone.presentation.ui.composables.LoadingModal
import com.louisngatale.twitterclone.presentation.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ExperimentalMaterialApi
@AndroidEntryPoint
class RegisterFragment : Fragment() {
    @Inject
    lateinit var application: MainApplication

    private val TAG: String = "Registration"


    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the view for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                val scrollState = rememberScrollState()
                val isDarkTheme: Boolean = isSystemInDarkTheme()
                val logo =  if (isDarkTheme) R.drawable.icon_white else R.drawable.icon_blue
                val loading = viewModel.loading.value

                TwitterCloneTheme {
                    Scaffold {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 25.dp, vertical = 10.dp)
                        ){
                            Column(modifier = Modifier.verticalScroll(scrollState)) {
                                // Email
                                var email by remember { mutableStateOf("") }
                                var emailErrorState by remember { mutableStateOf(false) }
                                var emailErrorMessage by remember { mutableStateOf("") }
                                // Password
                                var password by remember { mutableStateOf("") }
                                var pwdErrorState by remember { mutableStateOf(false) }
                                var pwdErrorMessage by remember { mutableStateOf("") }
                                // Username
                                var username by remember { mutableStateOf("") }
                                var uNameErrorState by remember { mutableStateOf(false) }
                                var uNameErrorMessage by remember { mutableStateOf("") }
                                // Phone number
                                var phoneNumber by remember { mutableStateOf("") }
                                var phnErrorState by remember { mutableStateOf(false) }
                                var phnErrorMessage by remember { mutableStateOf("") }
                                // DOB
//                                var dob by remember { mutableStateOf("") }
//                                var dobErrorState by remember { mutableStateOf(false) }
//                                var dobErrorMessage by remember { mutableStateOf("") }
                                // Full Name
                                var fullname by remember { mutableStateOf("") }
                                var nameErrorState by remember { mutableStateOf(false) }
                                var nameErrorMessage by remember { mutableStateOf("") }
                                // Confirm Password
                                var confirmPwd by remember { mutableStateOf("") }
                                var pwd2ErrorState by remember { mutableStateOf(false) }
                                var pwd2ErrorMessage by remember { mutableStateOf("") }

                                Spacer(modifier = Modifier.height(25.dp))

                                Image(
                                    painter = painterResource(id = logo),
                                    contentDescription = "Logo"
                                )

                                Spacer(modifier = Modifier.height(30.dp))

                                // Login Title
                                Text(
                                    text = "Create new account",
                                    style = MaterialTheme.typography.h5,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                /******* START REGISTRATION FORM *******/
                                // Full Name
                                OutlinedTextField(
                                    value = fullname,
                                    label = {
                                        Text(text = if (nameErrorState) nameErrorMessage else "Full Name")
                                    },
                                    onValueChange = {
                                        fullname = it
                                        when {
                                            fullname.isEmpty() -> {
                                                nameErrorState = true
                                                nameErrorMessage = "Email should not blank"
                                            }
                                            else -> {
                                                nameErrorState = false
                                                nameErrorMessage = ""
                                            }
                                        }
                                    },
                                    maxLines = 1,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    isError = nameErrorState
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                // User Name
                                OutlinedTextField(
                                    value = username,
                                    label = {
                                        Text(text = if (uNameErrorState) uNameErrorMessage else "User Name")
                                    },
                                    onValueChange = {
                                        username = it
                                        when {
                                            username.isEmpty() -> {
                                                uNameErrorState = true
                                                uNameErrorMessage = "Username should not blank"
                                            }
                                            else -> {
                                                uNameErrorState = false
                                                uNameErrorMessage = ""
                                            }
                                        }
                                    },
                                    maxLines = 1,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    isError = uNameErrorState
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                // User Name
                                OutlinedTextField(
                                    value = email,
                                    label = {
                                        Text(text = if (emailErrorState) emailErrorMessage else "Email")
                                    },
                                    onValueChange = {
                                        email = it
                                        when {
                                            email.isEmpty() -> {
                                                emailErrorState = true
                                                emailErrorMessage = "Email should not blank"
                                            }
                                            else -> {
                                                emailErrorState = false
                                                emailErrorMessage = ""
                                            }
                                        }
                                    },
                                    maxLines = 1,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    isError = emailErrorState
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                // Phone Number
                                OutlinedTextField(
                                    value = phoneNumber,
                                    label = {
                                        Text(text = if (phnErrorState) phnErrorMessage else "Phone Number")
                                    },
                                    onValueChange = {
                                        phoneNumber = it
                                        when {
                                            phoneNumber.isEmpty() -> {
                                                phnErrorState = true
                                                phnErrorMessage = "Phone should not be blank"
                                            }
                                            else -> {
                                                phnErrorState = false
                                                phnErrorMessage = ""
                                            }
                                        }
                                    },
                                    maxLines = 1,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    isError = phnErrorState
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                // Password
                                OutlinedTextField(
                                    isError = pwdErrorState,
                                    value = password,
                                    onValueChange = {
                                        password = it
                                        when {
                                            password.isEmpty() -> {
                                                pwdErrorState = true
                                                pwdErrorMessage = "Password should not blank"
                                            }
                                            else -> {
                                                pwdErrorState = false
                                                pwdErrorMessage = ""
                                            }
                                        }
                                    },
                                    label = {
                                        Text(text = if (pwdErrorState) pwdErrorMessage else "Password")
                                    },
                                    maxLines = 1,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    visualTransformation = PasswordVisualTransformation()
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                // Confirm Password
                                OutlinedTextField(
                                    isError = pwd2ErrorState,
                                    value = confirmPwd,
                                    onValueChange = {
                                        confirmPwd = it
                                        when {
                                            !confirmPwd.equals(password) -> {
                                                pwd2ErrorState = true
                                                pwdErrorMessage = "Passwords don't match"
                                            }
                                            else -> {
                                                pwd2ErrorState = false
                                                pwdErrorMessage = ""
                                            }
                                        }
                                    },
                                    label = {
                                        Text(text = if (pwd2ErrorState) pwdErrorMessage else "Confirm Password")
                                    },
                                    maxLines = 1,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    visualTransformation = PasswordVisualTransformation()
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                // Login Button
                                Button(
                                    onClick = {
                                        if(email.isNotEmpty() && password.isNotEmpty()){
                                            Log.d(TAG, "GOT HERE")
                                            val request = RegisterRequest(email = email, username = username,phone_number = phoneNumber,password = password,name = fullname,dob = "2000-08-12")
                                            viewModel.register(request)
                                        }else{
                                            if(email.isEmpty())
                                                emailErrorState = true
                                                emailErrorMessage = "Email should not blank"
                                            if(password.isEmpty())
                                                pwdErrorState = true
                                                pwdErrorMessage = "Password should not blank"
                                            if(confirmPwd.isEmpty())
                                                pwd2ErrorState = true
                                                pwd2ErrorMessage = "Password should not blank"
                                            if(username.isEmpty())
                                                uNameErrorState = true
                                                uNameErrorMessage = "Username should not blank"
//                                            if(dob.isEmpty())
//                                                dobErrorState = true
//                                                dobErrorMessage = "Password should not blank"
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(56.65.dp)
                                        .clip(shape = RoundedCornerShape(30.dp)),
                                    shape = TwitterShapes.large) {
                                    Text(
                                        text = "Register",
                                        color = White
                                    )
                                }

                                Spacer(modifier = Modifier.height(25.dp))

                                // Forgot password? Sign in
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center) {
                                    TextButton(onClick = { /*TODO*/ }) {
                                        Text(
                                            text = "Forgot password?",
                                            color = Blue200
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(6.dp))

                                    TextButton(onClick = {
                                        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                                        findNavController().navigate(action)
                                    }) {
                                        Text(
                                            text = "Login",
                                            color = Blue200
                                        )
                                    }

                                }
                            }

                            /******* END REGISTRATION FORM *******/

                            // Progress Bar
                            if(loading){
                                LoadingModal()
                            }
                        }
                    }
                }
            }
        }
    }
}

