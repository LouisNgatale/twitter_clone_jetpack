package com.louisngatale.twitterclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.louisngatale.twitterclone.domain.UserPreferences
import com.louisngatale.twitterclone.domain.utils.startNewActivity
import com.louisngatale.twitterclone.presentation.ui.authentication.AuthenticationActivity
import com.louisngatale.twitterclone.presentation.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var  userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_main)

        // TODO: Send to Home Screen

        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if (it == "") AuthenticationActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })
    }
}