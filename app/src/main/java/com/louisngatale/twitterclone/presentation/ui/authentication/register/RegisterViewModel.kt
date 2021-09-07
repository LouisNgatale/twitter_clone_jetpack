package com.louisngatale.twitterclone.presentation.ui.authentication.register

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.louisngatale.twitterclone.network.request.RegisterRequest
import com.louisngatale.twitterclone.repository.authentication.registration.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject
constructor(
    application: Application,
    private val registerRepository: RegisterRepository,
) : AndroidViewModel(application) {
    private val TAG: String = "Registration"
    val loading = mutableStateOf(false)

    fun register(registerRequest: RegisterRequest){
        loading.value = true
        Log.d(TAG, "Start Registration")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d(TAG, "Here!")
                val (message, errors) = registerRepository.register(registerRequest)
                loading.value = false
                if (errors != null){
                    // TODO: Handle error
                    Log.d(TAG, errors.toString())
                }
                if (message != null){
                    // Send to login page
//                    val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
//                    findNavController().navigate(action)
                    Log.d(TAG, message)
                }
            }catch (e: Exception){
                loading.value = false
                Log.e(TAG, "RegistrationJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }
}