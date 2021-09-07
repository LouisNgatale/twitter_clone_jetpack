package com.louisngatale.twitterclone.presentation.ui.authentication.login

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.louisngatale.twitterclone.R
import com.louisngatale.twitterclone.network.request.LoginRequest
import com.louisngatale.twitterclone.network.resource.Resource
import com.louisngatale.twitterclone.network.response.authentication.login.LoginResponse
import com.louisngatale.twitterclone.repository.authentication.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val authRepository: AuthRepository,
    application: Application,
): AndroidViewModel(application) {
    private val TAG: String = "Login"
    @SuppressLint("StaticFieldLeak")
    val context: Context = getApplication<Application>().applicationContext!!
    private val getResource: Resources = context.resources
    private val sharedPref: SharedPreferences = context
        .getSharedPreferences(getResource.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse : LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    val loading = mutableStateOf(false)
    val error = mutableStateOf("")
    val isError = mutableStateOf(false)
    val loggedIn = mutableStateOf(sharedPref.getString("loggedIn","false"))
    val snackbarVisibleState = mutableStateOf(false)
    val username = mutableStateOf(sharedPref.getString("username",""))

    fun login(
        email:String,
      password:String
    ) = viewModelScope.launch{
        loading.value = true
        viewModelScope.launch{
                val request = LoginRequest(email, password)
                _loginResponse.value = authRepository.login(request)
        }
    }

    fun saveAuthToken(token : String) = viewModelScope.launch {
        authRepository.saveAuthToken(token)
    }
}