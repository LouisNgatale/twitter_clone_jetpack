package com.louisngatale.twitterclone

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.louisngatale.twitterclone.domain.session_manager.SessionManager
import com.louisngatale.twitterclone.domain.utils.startNewActivity
import com.louisngatale.twitterclone.presentation.ui.authentication.AuthenticationActivity
import com.louisngatale.twitterclone.presentation.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var  sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_main)

        sessionManager.authToken.asLiveData().observe(this, Observer {
            Handler().postDelayed({
                val activity = if (it === null) AuthenticationActivity::class.java else HomeActivity::class.java
                startNewActivity(activity)
            },3000)
        })
    }
}