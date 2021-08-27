package com.louisngatale.twitterclone.presentation.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.louisngatale.twitterclone.network.request.LoginRequest
import com.louisngatale.twitterclone.repository.authentication.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun login(email:String, password:String){
        viewModelScope.launch {
            try {
                val request = LoginRequest(email, password)
                val login = loginRepository.login(request)
                Log.d(TAG, login.token)
            }catch (e: Exception){
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }
}