package com.louisngatale.twitterclone.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.louisngatale.twitterclone.R
import com.louisngatale.twitterclone.presentation.theme.Grey100
import com.louisngatale.twitterclone.presentation.theme.TwitterCloneTheme

@ExperimentalMaterialApi
@Composable
fun TweetPage(homeFragment: HomeFragment) {
    TwitterCloneTheme {
        // Single tweet
        Column {
            Row(
                Modifier
                    .padding(
                        vertical = 7.dp,
                        horizontal = 5.dp
                    )
                    .clickable(onClick = {
                        val action = HomeFragmentDirections.actionHomeFragmentToTweetFragment()
                        findNavController(homeFragment).navigate(action)
                    })
            ) {
                // Profile Image
                Image(
                    painter = painterResource(id = R.drawable.prof),
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentDescription = "Profile image")

                Spacer(modifier = Modifier.width(5.dp))

                Column {
                    // User details Row
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        // Tweet owner details
                        Box {
                            Row {
                                // Full Name
                                Text(text = "Username", style = MaterialTheme.typography.subtitle1, color = MaterialTheme.colors.onSurface)
                                Spacer(modifier = Modifier.width(5.dp))
                                // User handle
                                Text(text = "@Username", style = MaterialTheme.typography.subtitle1, color = Grey100)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "•", style = MaterialTheme.typography.subtitle1, color = Grey100)
                                Spacer(modifier = Modifier.width(5.dp))
                                // Time
                                Text(text = "7h", style = MaterialTheme.typography.subtitle1, color = Grey100)
                            }
                        }

                        // Action Icon
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            modifier = Modifier
                                .clickable(onClick = { /*TODO: Add Action*/ })
                                .height(24.dp),
                            tint = Grey100,
                            contentDescription = "Menu"
                        )
                    }

                    // Tweet Body Row
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                                " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
                                "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
                                " in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur" +
                                " sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
                                "mollit anim id est laborum.",
                        style = MaterialTheme.typography.body2,
                        maxLines = 8,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colors.onSurface)

                    // Image Row
                    Text(
                        text = "Image here",
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.body2,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Comment (comment count), retweet (retweet count) Like (like count) share
                        Box {
                            Row (verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.comment),
                                    modifier = Modifier
                                        .size(17.dp),
                                    contentDescription = "Comment"
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                // Comments count
                                Text(
                                    text = "6",
                                    color = MaterialTheme.colors.onSurface,
                                    style = MaterialTheme.typography.caption,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(40.dp))

                        Box {
                            Row (verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    modifier = Modifier
                                        .size(17.dp),
                                    painter = painterResource(id = R.drawable.retweet_inactive),
                                    contentDescription = "Retweet"
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                // Retweet count
                                Text(
                                    text = "6",
                                    color = MaterialTheme.colors.onSurface,
                                    style = MaterialTheme.typography.caption,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(40.dp))

                        Box {
                            Row (verticalAlignment = Alignment.CenterVertically){
                                Image(
                                    painter = painterResource(id = R.drawable.like_inactive),
                                    modifier = Modifier
                                        .size(17.dp),
                                    contentDescription = "Like"
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                // Likes Count
                                Text(
                                    text = "6",
                                    color = MaterialTheme.colors.onSurface,
                                    style = MaterialTheme.typography.caption,
                                )
                            }
                        }
                    }
                }
            }

            Row(
                Modifier
                    .padding(
                        vertical = 7.dp,
                        horizontal = 5.dp
                    )
            ) {

                // Profile Image
                Image(
                    painter = painterResource(id = R.drawable.prof),
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentDescription = "Profile image")

                Spacer(modifier = Modifier.width(5.dp))

                Column {
                    // User details Row
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        // Tweet owner details
                        Box {
                            Row {
                                // Full Name
                                Text(text = "Username", style = MaterialTheme.typography.subtitle1, color = MaterialTheme.colors.onSurface)
                                Spacer(modifier = Modifier.width(5.dp))
                                // User handle
                                Text(text = "@Username", style = MaterialTheme.typography.subtitle1, color = Grey100)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "•", style = MaterialTheme.typography.subtitle1, color = Grey100)
                                Spacer(modifier = Modifier.width(5.dp))
                                // Time
                                Text(text = "7h", style = MaterialTheme.typography.subtitle1, color = Grey100)
                            }
                        }

                        // Action Icon
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            modifier = Modifier
                                .clickable(onClick = { /*TODO: Add Action*/ })
                                .height(18.dp),
                            tint = Grey100,
                            contentDescription = "Menu"
                        )
                    }

                    // Tweet Body Row
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                                " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
                                "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
                                " in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur" +
                                " sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
                                "mollit anim id est laborum.",
                        style = MaterialTheme.typography.body2,
                        maxLines = 8,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colors.onSurface)

                    // Image Row
                    Text(
                        text = "Image here",
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.body2,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Comment (comment count), retweet (retweet count) Like (like count) share
                        Box {
                            Row (verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.comment),
                                    modifier = Modifier
                                        .size(17.dp),
                                    contentDescription = "Comment"
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                // Comments count
                                Text(
                                    text = "6",
                                    color = MaterialTheme.colors.onSurface,
                                    style = MaterialTheme.typography.caption,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(40.dp))
                        Box {
                            Row (verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    modifier = Modifier
                                        .size(17.dp),
                                    painter = painterResource(id = R.drawable.retweet_inactive),
                                    contentDescription = "Retweet"
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                // Retweet count
                                Text(
                                    text = "6",
                                    color = MaterialTheme.colors.onSurface,
                                    style = MaterialTheme.typography.caption,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(40.dp))
                        Box {
                            Row (verticalAlignment = Alignment.CenterVertically){
                                Image(
                                    painter = painterResource(id = R.drawable.like_active),
                                    modifier = Modifier
                                        .size(17.dp),
                                    contentDescription = "Like"
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                // Likes Count
                                Text(
                                    text = "6",
                                    color = MaterialTheme.colors.onSurface,
                                    style = MaterialTheme.typography.caption,
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2,showSystemUi = true)
fun TweetPagePreview(){
    TwitterCloneTheme {
//        TweetPage(this@HomeFragment)
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2,showSystemUi = true)
fun TweetPagePreviewDark(){
    TwitterCloneTheme(isDarkTheme = true) {
//        TweetPage(this@HomeFragment)
    }
}