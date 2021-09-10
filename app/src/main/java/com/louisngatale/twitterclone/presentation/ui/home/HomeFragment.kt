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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import com.louisngatale.twitterclone.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.findNavController
import com.louisngatale.twitterclone.domain.session_manager.SessionManager
import com.louisngatale.twitterclone.domain.utils.startNewActivity
import com.louisngatale.twitterclone.presentation.theme.Blue200
import com.louisngatale.twitterclone.presentation.theme.Grey
import com.louisngatale.twitterclone.presentation.theme.Grey100
import com.louisngatale.twitterclone.presentation.theme.TwitterCloneTheme
import com.louisngatale.twitterclone.presentation.ui.authentication.AuthenticationActivity
import com.louisngatale.twitterclone.presentation.ui.composables.NavigationItem
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
        val routeId = findNavController().currentDestination?.id
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
                        bottomBar = {
                            CustomBottomBar(isDarkTheme,routeId)
                        })
                }
            }
        }
    }

    @Composable
    fun CustomBottomBar(isDarkTheme: Boolean, routeId: Int?) {
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Search,
            NavigationItem.Notifications,
            NavigationItem.Messages
        )

        Column {
            Divider(modifier = Modifier.fillMaxWidth())

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = MaterialTheme.colors.background)
            ) {
                // Home
                if(routeId == R.id.homeFragment){
                    // Home
                    Icon(
                        imageVector = Icons.Default.Home,
                        modifier = Modifier
                            .clickable(onClick = { /*TODO: Add Action*/ })
                            .height(27.dp),
                        tint = Blue200,
                        contentDescription = "Home"
                    )
                }else{
                    // Home
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        modifier = Modifier
                            .clickable(onClick = { /*TODO: Add Action*/ })
                            .height(27.dp),
                        tint = Grey100,
                        contentDescription = "Home"
                    )
                }

                // Search
                if(routeId == R.id.searchFragment){
                    Icon(
                        imageVector = Icons.Default.Search,
                        modifier = Modifier
                            .clickable(onClick = { /*TODO: Add Action*/ })
                            .height(27.dp),
                        tint = Blue200,
                        contentDescription = "Home"
                    )
                }else{
                    Icon(
                        imageVector = Icons.Default.Search,
                        modifier = Modifier
                            .clickable(onClick = { /*TODO: Add Action*/ })
                            .height(27.dp),
                        tint = Grey100,
                        contentDescription = "Home"
                    )
                }

                // Notifications
                if (routeId == R.id.notificationsFragment){
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        modifier = Modifier
                            .clickable(onClick = { /*TODO: Add Action*/ })
                            .height(27.dp),
                        tint = Blue200,
                        contentDescription = "Home"
                    )
                }else{
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        modifier = Modifier
                            .clickable(onClick = { /*TODO: Add Action*/ })
                            .height(27.dp),
                        tint = Grey100,
                        contentDescription = "Home"
                    )
                }

                // Messages
                if (routeId == R.id.messagesFragment){
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        modifier = Modifier
                            .clickable(onClick = { /*TODO: Add Action*/ })
                            .height(27.dp),
                        tint = Blue200,
                        contentDescription = "Home"
                    )
                }else{
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        modifier = Modifier
                            .clickable(onClick = { /*TODO: Add Action*/ })
                            .height(27.dp),
                        tint = Grey100,
                        contentDescription = "Home"
                    )
                }

                /*Image(
                    painter = painterResource(id = R.drawable.home_active),
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(20.dp))

                // Search
                Image(
                    painter = painterResource(id = R.drawable.search_inactive),
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(20.dp))

                // Notifications
                Image(
                    painter = painterResource(id = R.drawable.notification_inactive),
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(20.dp))

                // Messages
                Image(
                    painter = painterResource(id = R.drawable.messages_inactive),
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(20.dp))
*/
            }
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