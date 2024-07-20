package com.turitsynanton.android.wbtech.ui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.turitsynanton.android.wbtech.data.dataMeetingTags
import com.turitsynanton.android.wbtech.data.dataMeetings
import com.turitsynanton.android.wbtech.data.tabs1
import com.turitsynanton.android.wbtech.navigation.meetings
import com.turitsynanton.android.wbtech.ui.screens.mainscreens.ScreenCommunities
import com.turitsynanton.android.wbtech.ui.screens.additionalscreens.ScreenCommunityDetails
import com.turitsynanton.android.wbtech.ui.screens.additionalscreens.ScreenMeetingDetails
import com.turitsynanton.android.wbtech.ui.screens.mainscreens.ScreenMeetings
import com.turitsynanton.android.wbtech.ui.screens.mainscreens.ScreenMoreMenu
import com.turitsynanton.android.wbtech.ui.screens.additionalscreens.ScreenProfile

@Preview(showBackground = true)
@Composable
fun PreviewCommonMeetings() {
    ScreenMeetings(
        modifier = Modifier,
        tabs = tabs1,
//        meetingsList = meetings
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommunities() {
    ScreenCommunities(
        modifier = Modifier
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoreMenu() {
    val navController = rememberNavController()
    ScreenMoreMenu(
        modifier = Modifier,
        navController,
        onProfileScreen = {},
        onThemeScreen = {},
        onNotificationScreen = {},
        onSafetyScreen = {}
    )
}

@Preview(showBackground = true)
@Composable
fun Profile() {
    ScreenProfile()
}

@Preview(showBackground = true)
@Composable
fun CommunityDetails() {
    val navController = rememberNavController()
    ScreenCommunityDetails(meetingsList = meetings, Modifier, navController = navController) {

    }
}

@Preview(showBackground = true)
@Composable
fun MeetingDetails() {
    val navController = rememberNavController()
    ScreenMeetingDetails(
        modifier = Modifier,
        dataMeetingTags,
        navController = navController
    )
}