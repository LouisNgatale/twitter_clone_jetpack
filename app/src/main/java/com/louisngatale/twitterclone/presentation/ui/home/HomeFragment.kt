package com.louisngatale.twitterclone.presentation.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import com.louisngatale.twitterclone.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.louisngatale.twitterclone.domain.session_manager.SessionManager
import com.louisngatale.twitterclone.domain.utils.startNewActivity
import com.louisngatale.twitterclone.presentation.theme.Blue200
import com.louisngatale.twitterclone.presentation.theme.Grey
import com.louisngatale.twitterclone.presentation.theme.TwitterCloneTheme
import com.louisngatale.twitterclone.presentation.ui.authentication.AuthenticationActivity
import com.louisngatale.twitterclone.repository.authentication.AuthRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalMaterialApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var repository: AuthRepository

    private lateinit  var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext())
            .apply {
            setContent {
                TwitterCloneTheme {
                    val isDarkTheme: Boolean = isSystemInDarkTheme()
                    val scrollState = rememberScrollState()
                    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                    val scope = rememberCoroutineScope() // in 1.0.0-beta `open()` and `close` are suspend functions

                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            AppBar(isDarkTheme = isDarkTheme, scaffoldState, scope)
                        },
                        floatingActionButtonPosition = FabPosition.End,
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {},
                                backgroundColor = Blue200,
//                                contentColor = White
                            ) {
                                Image(painter = painterResource(id = R.drawable.new_tweet), modifier = Modifier.size(25.dp),contentDescription = "New Tweet" )
                            }
                        },
                        drawerContent = { Text(text = "drawerContent") },
                        drawerBackgroundColor = MaterialTheme.colors.background,
                        content = { TweetPage(this@HomeFragment) },
                        bottomBar = { CustomBottomBar(isDarkTheme) })
                }
            }
        }
    }

    @Composable
    fun CustomBottomBar(isDarkTheme: Boolean) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = MaterialTheme.colors.background)
                .drawBehind {

                    val strokeWidth = 1 * density
                    val y = size.height - strokeWidth / 2

                    drawLine(
                        Grey,
                        Offset(0f, y),
                        Offset(size.width, y),
                        strokeWidth
                    )
                }
                .padding(horizontal = 7.dp)

        ) {
            Image(
                painter = painterResource(R.drawable.prof),
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape),
                contentDescription = null
            )

            Image(
                painter = painterResource(id = R.drawable.icon_blue),
                modifier = Modifier
                    .size(25.dp),
                contentDescription = null
            )

            Image(
                painter = painterResource(id = R.drawable.timeline_settings),
                modifier = Modifier
                    .size(25.dp),
                contentDescription = null
            )

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        sessionManager = SessionManager(requireContext())
    }

    @Composable
    fun AppBar(
        isDarkTheme: Boolean,
        scaffoldState: ScaffoldState,
        scope: CoroutineScope,
    ) {
        Divider(color = Color.Red, thickness = 1.dp)

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = MaterialTheme.colors.background)
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
                .padding(horizontal = 7.dp)

        ) {
            Image(
                painter = painterResource(R.drawable.prof),
                modifier = Modifier
                    .size(25.dp)
                    .clickable(
                        onClick = {
                            scope.launch { scaffoldState.drawerState.open() }
                        }
                    )
                    .clip(CircleShape),
                contentDescription = null
            )

            Image(
                painter = painterResource(id = R.drawable.icon_blue),
                modifier = Modifier
                    .size(25.dp),
                contentDescription = null
            )

            Image(
                painter = painterResource(id = R.drawable.timeline_settings),
                modifier = Modifier
                    .clickable(onClick = {
                        scope.launch {
                            // Handle login in ViewModel
                            val authToken = sessionManager.authToken.first()
                            sessionManager.revokeToken()
                            if (authToken != null) {
                                repository.logout(token = authToken)
                                requireActivity().startNewActivity(AuthenticationActivity::class.java)
                            }
                        }
                    })
                    .size(25.dp),
                contentDescription = null
            )

        }
    }

}