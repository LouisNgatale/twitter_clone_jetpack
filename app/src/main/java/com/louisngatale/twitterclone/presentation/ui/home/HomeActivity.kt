package com.louisngatale.twitterclone.presentation.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.louisngatale.twitterclone.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        setContentView(R.layout.activity_home)
    }
}