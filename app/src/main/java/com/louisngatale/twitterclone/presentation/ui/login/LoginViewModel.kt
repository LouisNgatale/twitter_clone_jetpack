package com.louisngatale.twitterclone.presentation.ui.login

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.louisngatale.twitterclone.R
import com.louisngatale.twitterclone.network.request.LoginRequest
import com.louisngatale.twitterclone.repository.authentication.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val loginRepository: LoginRepository,
    application: Application,
): AndroidViewModel(application) {
    private val TAG: String = "Login"
    @SuppressLint("StaticFieldLeak")
    val context: Context = getApplication<Application>().applicationContext!!
    private val getResource: Resources = context.resources
    private val sharedPref: SharedPreferences = context
        .getSharedPreferences(getResource.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    // Live Data
    val loading = mutableStateOf(false)
    val error = mutableStateOf("")
    val isError = mutableStateOf(false)
    val snackbarVisibleState = mutableStateOf(false)
    val username = mutableStateOf(sharedPref.getString("username",""))

    fun login(email:String, password:String){
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // TODO: Request timeout
                val request = LoginRequest(email, password)
                val login = loginRepository.login(request)
                if (login.status == "Error"){
                    snackbarVisibleState.value = true
                    error.value = login.response_message
                    loading.value = false
                }else{
                    // TODO: Convert data to User model and store the user
                    with (sharedPref.edit()) {
                        putString("user_id", login.user_id)
                        putString("username", login.username)
                        putString("email", login.email)
                        putString("first_name", login.first_name)
                        putString("second_name", login.second_name)
                        putString("profile_picture", login.profile_picture)
                        putString("token", login.token)
                        apply()
                    }
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