package com.louisngatale.twitterclone.domain.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.compose.material.ExperimentalMaterialApi
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.louisngatale.twitterclone.network.resource.Resource
import com.louisngatale.twitterclone.presentation.ui.authentication.login.LoginFragment

fun <A : Activity> Activity.startNewActivity(activity: Class<A>){
    Intent( this,activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
        finish()
    }
}

fun View.visible(isVisible : Boolean){
    visibility = if(isVisible) View.VISIBLE else View.GONE
}

fun View.snackbar(message: String, action: (() -> Unit)? = null){
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }
    }
    snackbar.show()
}

@ExperimentalMaterialApi
fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
){
//        failure.isNetworkError -> requireView().snackbar("Please check your internet connection", retry)
    when{
        failure.errorCode == 401 -> {
            if (this is LoginFragment){
                requireView().snackbar("You've entered incorrect email or password")
            }else{
                //TODO: perform logout operation here
            }
        }
        failure.errorCode == 422 -> {
            requireView().snackbar("You've entered invalid details")
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}