package com.louisngatale.twitterclone.presentation.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.louisngatale.twitterclone.R
import com.louisngatale.twitterclone.domain.UserPreferences
import com.louisngatale.twitterclone.domain.utils.startNewActivity
import com.louisngatale.twitterclone.presentation.ui.authentication.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        setContentView(R.layout.activity_home)
    }
}