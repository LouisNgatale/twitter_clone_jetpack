package com.louisngatale.twitterclone.presentation.ui.login

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.louisngatale.twitterclone.network.request.LoginRequest
import com.louisngatale.twitterclone.repository.authentication.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val loginRepository: LoginRepository,
): ViewModel() {
    private val TAG: String = "Login"
    val loading = mutableStateOf(false)
    val error = mutableStateOf("")
    val isError = mutableStateOf(false)

    fun login(email:String, password:String){
        loading.value = true
        viewModelScope.launch {
            try {
                // Request timeout
                delay(2000)
                val request = LoginRequest(email, password)
                val login = loginRepository.login(request)
                if (login.status == "Error"){
                    error.value = login.response_message
                    isError.value = true
                    loading.value = false
                    delay(2000)
                    error.value = ""
                    isError.value = false
                }else{
                    // TODO: Convert data to User model and store temporarily
                    Log.d(TAG, login.toString())
                    loading.value = false
                }
            }catch (e: Exception){
                loading.value = false
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }
}