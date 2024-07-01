package com.turitsynanton.android.wbtech.ui.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.data.Community
import com.turitsynanton.android.wbtech.ui.components.СommunityCard

@Composable
fun CommunityList(communityList: List<Community>) {
    LazyColumn(
        Modifier
            .padding(vertical = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(communityList.size) { index ->
            СommunityCard(
                modifier = Modifier,
                resId = R.drawable.ic_designa,
                text = communityList[index].name,
                communitySize = communityList[index].size
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = 12.dp),
                thickness = 1.dp,
                color = Color(0xFFEDEDED)
            )
        }
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
)

@Preview(showBackground = true)
@Composable
fun Show() {
    CommunityList(communityList = communities)
}