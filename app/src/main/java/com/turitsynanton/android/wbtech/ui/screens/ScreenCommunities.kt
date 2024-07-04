package com.turitsynanton.android.wbtech.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.wbtech.data.Community
import com.turitsynanton.android.wbtech.ui.items.SearchField
import com.turitsynanton.android.wbtech.ui.organisms.CommunityList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenCommunities(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
    ) {
        SearchField(modifier = Modifier, true)
        CommunityList(communityList = communities)
    }
}

val communities = listOf(
    Community(
        "Developer comm",
        "10112",
    ),
    Community(
        "Android comm",
        "10",
    ),
    Community(
        "Kotlin comm",
        "19348275",
    ),
    Community(
        "Developer comm",
        "10112",
    ),
    Community(
        "Android comm",
        "10",
    ),
    Community(
        "Kotlin comm",
        "19348275",
    ),
    Community(
        "Developer comm",
        "10112",
    ),
    Community(
        "Android comm",
        "10",
    ),
    Community(
        "Kotlin comm",
        "19348275",
    ),
    Community(
        "Developer comm",
        "10112",
    ),
    Community(
        "Android comm",
        "10",
    ),
    Community(
        "Kotlin comm",
        "19348275",
    ),
    Community(
        "Developer comm",
        "10112",
    ),
    Community(
        "Android comm",
        "10",
    ),
    Community(
        "Kotlin comm",
        "19348275",
    ),
    Community(
        "Developer comm",
        "10112",
    ),
    Community(
        "Android comm",
        "10",
    ),
    Community(
        "LAST",
        "19348275",
    )
)
