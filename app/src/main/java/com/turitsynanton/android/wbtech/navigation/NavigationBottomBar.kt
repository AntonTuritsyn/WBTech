package com.turitsynanton.android.wbtech.navigation

import com.turitsynanton.android.wbtech.R

sealed class NavigationBottomBar(var route: String, var icon: Int, var title: String) {
    data object Meetings : NavigationBottomBar("meetings", R.drawable.ic_meetings, "Встречи")
    data object MeetingsScreen : NavigationBottomBar("meetingsScreen", R.drawable.ic_meetings, "")
    data object Communities : NavigationBottomBar("communities", R.drawable.ic_society, "Сообщества")
    data object CommunitiesScreen : NavigationBottomBar("communitiesScreen", R.drawable.ic_society, "")
    data object More : NavigationBottomBar("more", R.drawable.ic_more, "Ещё")
    data object MoreScreen : NavigationBottomBar("moreScreen", R.drawable.ic_more, "")
}