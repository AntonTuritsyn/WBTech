package com.turitsynanton.android.wbtech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.turitsynanton.android.wbtech.data.meetingTag
import com.turitsynanton.android.wbtech.ui.items.Avatars
import com.turitsynanton.android.wbtech.ui.items.CustomAvatar
import com.turitsynanton.android.wbtech.ui.items.MyElevatedButton
import com.turitsynanton.android.wbtech.ui.items.MyFilledButton
import com.turitsynanton.android.wbtech.ui.items.MyFilterChip
import com.turitsynanton.android.wbtech.ui.items.MyOutlinedButton
import com.turitsynanton.android.wbtech.ui.items.MyTextButton
import com.turitsynanton.android.wbtech.ui.items.MyTonalButton
import com.turitsynanton.android.wbtech.ui.items.SearchField
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.drafts.ToTry
import com.turitsynanton.android.wbtech.ui.components.MeetingCard
import com.turitsynanton.android.wbtech.ui.components.People
import com.turitsynanton.android.wbtech.ui.components.СommunityCard
import com.turitsynanton.android.wbtech.navigation.NavGraph
import com.turitsynanton.android.wbtech.ui.organisms.BottomBar
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.ui.theme.WBTechTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            WBTechTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomBar(navController = navController)
                    }
                ) {
                    NavGraph(navController = navController, modifier = Modifier.padding(it))
                }
            }
        }
    }
}


@Composable
fun Draft(modifier: Modifier) {
    LazyColumn() {
        items(1) {
            LazyRow {
                item {
                    MyFilledButton(modifier = Modifier, "Button", Color.Unspecified)
                    MyElevatedButton(modifier = Modifier, "Button")
                    MyTonalButton(modifier = Modifier, "Button", Color.Unspecified)
                    MyOutlinedButton(modifier = Modifier, "Button", Color.DarkGray)
                    MyTextButton(modifier = Modifier, "Button")
                    MyFilterChip(modifier = Modifier, "Python")
                }
            }

            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                32.sp,
                FontWeight.Bold,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                24.sp,
                FontWeight.Bold,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                18.sp,
                FontWeight.SemiBold,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                16.sp,
                FontWeight.SemiBold,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                14.sp,
                FontWeight.SemiBold,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                14.sp,
                FontWeight.Normal,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                12.sp,
                FontWeight.Normal,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                10.sp,
                FontWeight.Normal,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                10.sp,
                FontWeight.SemiBold,
                FontStyle.Normal,
                Color.Unspecified
            )

            SearchField(modifier = Modifier)
            Avatars(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp), R.drawable.icon_variant_user
            )
            /*CustomAvatar(
                modifier = Modifier,
                variant = 2,
                resId = R.drawable.icon_variant_user
            )*/
            ToTry()
            People(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp),
                100
            )
            MeetingCard(
                modifier = Modifier,
                resId = R.drawable.ic_meeting,
                "Developer meeting",
                false,
                "11.09.2024",
                "Москва",
                emptyList()
            )
            СommunityCard(
                modifier = Modifier,
                resId = R.drawable.ic_designa,
                "Developer meeting",
                "10000"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyColumn() {
        items(1) {
            LazyRow {
                item {
                    MyFilledButton(modifier = Modifier, "Button", Color.Unspecified)
                    MyElevatedButton(modifier = Modifier, "Button")
                    MyTonalButton(modifier = Modifier, "Button", Color.Unspecified)
                    MyOutlinedButton(modifier = Modifier, "Button", Color.DarkGray)
                    MyTextButton(modifier = Modifier, "Button")
                    MyFilterChip(modifier = Modifier, "Python")
                }
            }

            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                32.sp,
                FontWeight.Bold,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                24.sp,
                FontWeight.Bold,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                18.sp,
                FontWeight.SemiBold,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                16.sp,
                FontWeight.SemiBold,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                14.sp,
                FontWeight.SemiBold,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                14.sp,
                FontWeight.Normal,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                12.sp,
                FontWeight.Normal,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                10.sp,
                FontWeight.Normal,
                FontStyle.Normal,
                Color.Unspecified
            )
            SomeText(
                Modifier,
                "The quick brown fox jumps over the lazy dog",
                SfProDisplay,
                10.sp,
                FontWeight.SemiBold,
                FontStyle.Normal,
                Color.Unspecified
            )
            SearchField(modifier = Modifier)
            Avatars(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp), R.drawable.icon_variant_user
            )
            /*CustomAvatar(
                modifier = Modifier,
                variant = 3,
                resId = R.drawable.icon_variant_user
            )*/
            ToTry()
            People(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp),
                100
            )
            СommunityCard(
                modifier = Modifier,
                resId = R.drawable.ic_designa,
                "Developer meeting",
                "10000"
            )
            MeetingCard(
                modifier = Modifier,
                resId = R.drawable.ic_meeting,
                meetingName = "Developer meeting",
                ended = false,
                meetingDate = "13.09.2024",
                meetingCity = "Москва",
                meetingTags = meetingTag
            )
        }
    }
}