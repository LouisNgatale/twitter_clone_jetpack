package com.louisngatale.twitterclone.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.louisngatale.twitterclone.presentation.theme.Black100
import com.louisngatale.twitterclone.presentation.theme.Blue200
import com.louisngatale.twitterclone.presentation.theme.TwitterShapes

@Composable
fun LoadingModal(){
    // Progress Bar
    Box (
        Modifier.fillMaxSize(),
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(
                        color = Black100,
                        shape = TwitterShapes.medium
                    )
                    .padding(horizontal = 40.dp, vertical = 20.dp)
            ){
                Text(text = "Loading", color = Blue200, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(15.dp))
                CircularProgressIndicator()
            }
        }
    }
}