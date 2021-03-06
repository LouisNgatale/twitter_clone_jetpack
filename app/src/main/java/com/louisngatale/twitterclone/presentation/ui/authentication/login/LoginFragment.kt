package com.louisngatale.twitterclone.presentation.ui.authentication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.louisngatale.twitterclone.MainApplication
import com.louisngatale.twitterclone.R
import com.louisngatale.twitterclone.domain.session_manager.SessionManager
import com.louisngatale.twitterclone.domain.utils.handleApiError
import com.louisngatale.twitterclone.domain.utils.startNewActivity
import com.louisngatale.twitterclone.network.resource.Resource
import com.louisngatale.twitterclone.presentation.theme.*
import com.louisngatale.twitterclone.presentation.ui.composables.LoadingModal
import com.louisngatale.twitterclone.presentation.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@ExperimentalMaterialApi
@AndroidEntryPoint
class LoginFragment : Fragment() {
    @Inject
    lateinit var application: MainApplication

    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var  sessionManager: SessionManager
//    private val viewModel: LoginViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    lifecycleScope.launch{
                        viewModel.saveAuthToken(it.value.user.accessToken)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                        viewModel.loading.value = false
                    }
                }
                is Resource.Failure -> {
                    handleApiError(it)
                    viewModel.loading.value = false
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sessionManager = SessionManager(requireContext())

        return ComposeView(requireContext()).apply {
            setContent {
                // Dark mode setup
                val isDarkTheme: Boolean = isSystemInDarkTheme()
                val logo =  if (isDarkTheme) R.drawable.icon_white else R.drawable.icon_blue
                val loading = viewModel.loading.value
                val scrollState = rememberScrollState()
                val navController: NavController = findNavController()

                TwitterCloneTheme {
                    Scaffold(
                        contentColor = MaterialTheme.colors.onSurface,
                        content = { LoginPage(scrollState,logo,loading,navController) }
                    )
                }
            }
        }
    }

    @Composable
    fun LoginPage(
        scrollState: ScrollState,
        logo: Int,
        loading: Boolean,
        navController: NavController
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 10.dp),
        ){
            Column (
                modifier = Modifier
                    .verticalScroll(scrollState)
            ){
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var errorState by remember { mutableStateOf(false)}
                var errorMessage by remember { mutableStateOf("")}
                var pwdErrorState by remember { mutableStateOf(false)}
                var pwdErrorMessage by remember { mutableStateOf("")}

                Spacer(modifier = Modifier.height(25.dp))

                Image(
                    painter = painterResource(id = logo),
                    contentDescription = "Logo"
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Login Title
                Text(
                    text = "Login to twitter",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Login Form
                // Email
                OutlinedTextField(
                    value = email,
                    label = {
                        Text(text = if (errorState) errorMessage else "Email or username")
                    },
                    onValueChange = {
                        email = it
                        when {
                            email.isEmpty() -> {
                                errorState = true
                                errorMessage = "Email should not blank"
                            }
                            else -> {
                                errorState = false
                                errorMessage = ""
                            }
                        }
                    },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth(),
                    isError = errorState
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

                // Login Button
                Button(
                    onClick = {
                        if(email.isNotEmpty() && password.isNotEmpty()){
                            viewModel.login(email,password )
                        }else{
                            if(email.isEmpty())
                                errorState = true
                            errorMessage = "Email should not blank"
                            if(password.isEmpty())
                                pwdErrorState = true
                            pwdErrorMessage = "Password should not blank"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.65.dp)
                        .clip(shape = RoundedCornerShape(30.dp)),
                    shape = TwitterShapes.large) {
                    Text(
                        text = "Login",
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
                        val action = LoginFragmentDirections.actionLoginFragment2ToRegisterFragment2()
                        navController.navigate(action)
                    }) {
                        Text(
                            text = "Sign up for Twitter",
                            color = Blue200
                        )
                    }

                }
            }

            // Progress Bar
            if(loading){
                LoadingModal()
            }
        }

    }
}
