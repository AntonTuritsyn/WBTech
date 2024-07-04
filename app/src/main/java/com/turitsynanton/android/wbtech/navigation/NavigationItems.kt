package com.turitsynanton.android.wbtech.navigation

import com.turitsynanton.android.wbtech.R

sealed class NavigationItems(var route: String, var icon: Int, var title: String) {
    data object Meetings : NavigationItems("meetings", R.drawable.ic_meetings, "Встречи")
    data object Communities : NavigationItems("communities", R.drawable.ic_society, "Сообщества")
    data object More : NavigationItems("more", R.drawable.ic_more, "Ещё")
}