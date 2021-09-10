package com.louisngatale.twitterclone.presentation.ui.home.tweet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.louisngatale.twitterclone.R
import com.louisngatale.twitterclone.presentation.theme.Grey100
import com.louisngatale.twitterclone.presentation.theme.TwitterCloneTheme

@Composable
fun TweetView() {
    Column (
        Modifier
            .padding(vertical = 9.dp, horizontal = 9.dp)
    ) {
        // User sub profile
        Row (modifier=Modifier.fillMaxWidth()){
            // Profile Image
            Image(
                painter = painterResource(id = R.drawable.prof),
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentDescription = "Profile image")

            Spacer(modifier = Modifier.width(5.dp))

            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    // Full Name
                    Text(text = "Username", style = MaterialTheme.typography.subtitle1, color = MaterialTheme.colors.onSurface)
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
                // User handle
                Text(text = "@Username", style = MaterialTheme.typography.subtitle1, color = Grey100)
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        // Tweet Body
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                    " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
                    "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
                    " in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur" +
                    " sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
                    "mollit anim id est laborum.",
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Normal,
            maxLines = 8,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onSurface
        )

        Spacer(modifier = Modifier.height(5.dp))

        // Tweet time details
        Row {
            // Time
            Text(
                text = "15:40",
                style = MaterialTheme.typography.subtitle1,
                color = Grey100,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(text = "•", style = MaterialTheme.typography.subtitle1, color = Grey100)

            Spacer(modifier = Modifier.width(5.dp))

            // Date
            Text(
                text = "09 Sep 21",
                style = MaterialTheme.typography.subtitle1,
                color = Grey100,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(text = "•", style = MaterialTheme.typography.subtitle1, color = Grey100)

            Spacer(modifier = Modifier.width(5.dp))

            // Tweet Device
            Text(
                text = "Tweet for android",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Normal
            )

        }

        Spacer(modifier = Modifier.height(10.dp))

        // Tweet Details Count
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "1 Retweet",color = MaterialTheme.colors.onSurface)
            Spacer(modifier = Modifier.width(10.dp))

            Text(text = "1 Quote Tweet",color = MaterialTheme.colors.onSurface)
            Spacer(modifier = Modifier.width(10.dp))

            Text(text = "16 Likes",color = MaterialTheme.colors.onSurface)
        }

        // Tweet actions
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
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

        Spacer(modifier = Modifier.height(10.dp))

        Divider(modifier = Modifier.fillMaxWidth())

    }
}

@Preview(showSystemUi = true)
@Composable
fun TweetViewPreview() {
    TwitterCloneTheme {
        TweetView()
    }
}