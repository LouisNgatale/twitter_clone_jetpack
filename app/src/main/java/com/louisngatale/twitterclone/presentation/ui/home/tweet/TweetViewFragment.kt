package com.louisngatale.twitterclone.presentation.ui.home.tweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import com.louisngatale.twitterclone.R
import com.louisngatale.twitterclone.presentation.theme.Black
import com.louisngatale.twitterclone.presentation.theme.Grey
import com.louisngatale.twitterclone.presentation.theme.TwitterCloneTheme
import kotlinx.coroutines.launch

class TweetViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                TwitterCloneTheme {
                    val scaffoldState = rememberScaffoldState()
                    // Create a coroutineScope for the animation
                    val coroutineScope = rememberCoroutineScope()
                    val isDarkTheme: Boolean = isSystemInDarkTheme()

                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBar(
                                title = {Text("Tweet")},
                                backgroundColor = if (isDarkTheme) MaterialTheme.colors.background else Color.White,
                                navigationIcon = {
                                    IconButton(onClick = {
                                        findNavController().popBackStack()
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = "Back",
                                        )
                                    }
                                },
                                modifier = Modifier
                                    .drawBehind {
                                    val strokeWidth = 1 * density
                                    val y = size.height + strokeWidth / 2

                                    drawLine(
                                        Grey,
                                        Offset(0f, y),
                                        Offset(size.width, y),
                                        strokeWidth
                                    )
                                }
                            )
                         },
//                        bottomBar = {
//                            BottomAppBar(cutoutShape = CircleShape) {
//                                IconButton(
//                                    onClick = {
//                                        coroutineScope.launch { scaffoldState.drawerState.open() }
//                                    }
//                                ) {
//                                    Icon(Icons.Filled.Menu, contentDescription = "....")
//                                }
//                            }
//                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        isFloatingActionButtonDocked = true,
                        content = { innerPadding ->
                            TweetView()
                        }
                    )
                }
            }
        }
    }

}