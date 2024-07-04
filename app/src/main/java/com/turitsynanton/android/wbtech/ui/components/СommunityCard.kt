package com.turitsynanton.android.wbtech.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.ui.items.Avatar
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
fun СommunityCard(modifier: Modifier, resId: Int, text: String, communitySize: String) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
    ) {
        Avatar(
            modifier = modifier
                .padding(4.dp)
                .size(48.dp),
            resId = resId,
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 16.dp)
        ) {
            SomeText(
                modifier = modifier
                    .padding(vertical = 4.dp),
                text = text,
                fontFamily = SfProDisplay,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = Color.Unspecified
            )
            SomeText(
                modifier = modifier
                    .padding(vertical = 2.dp),
                text = "$communitySize человек",
                fontFamily = SfProDisplay,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = Color(0xFFA4A4A4)
            )
        }
    }
}