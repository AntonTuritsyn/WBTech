package com.turitsynanton.android.wbtech.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.ui.items.CustomAvatar
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
fun MyProfileItem(modifier: Modifier, name: String, phone: String, avatar: Int) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable {  },
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomAvatar(modifier = modifier, variant = 1, resId = avatar)
        Column(
            Modifier
                .padding(start = 20.dp)
        ) {
            SomeText(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                text = name,
                fontFamily = SfProDisplay,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                color = Color(0xFF29183B)
            )
            SomeText(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                text = phone,
                fontFamily = SfProDisplay,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = Color(0xFFADB5BD)
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight, contentDescription = "")
    }
}

@Preview(showBackground = true)
@Composable
fun MyProfileItemPreview() {
    MyProfileItem(
        Modifier,
        "Anton Turitsyn",
        "+7 999 999-99-99",
        R.drawable.my_photo
    )
}