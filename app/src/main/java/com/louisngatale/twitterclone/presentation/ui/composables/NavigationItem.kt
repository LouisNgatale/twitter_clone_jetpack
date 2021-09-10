package com.louisngatale.twitterclone.presentation.ui.composables

import com.louisngatale.twitterclone.R

sealed class NavigationItem(
    var route: String,
    var icon: Int,
    var title: String
){
    object Home : NavigationItem("home", R.drawable.home_inactive, "Home")
    object Search : NavigationItem("search", R.drawable.search_inactive, "Search")
    object Notifications : NavigationItem("notifications", R.drawable.notification_inactive, "Notifications")
    object Messages : NavigationItem("messages", R.drawable.messages_inactive, "Messages")
}
