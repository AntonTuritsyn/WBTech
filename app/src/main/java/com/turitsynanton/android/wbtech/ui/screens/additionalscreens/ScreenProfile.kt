package com.turitsynanton.android.wbtech.ui.screens.additionalscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.ui.items.CustomAvatar
import com.turitsynanton.android.wbtech.ui.items.MyIconButton
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.theme.BrandColorDefault
import com.turitsynanton.android.wbtech.ui.theme.NeutralActive
import com.turitsynanton.android.wbtech.ui.theme.NeutralDisabled
import com.turitsynanton.android.wbtech.ui.theme.NeutralWhite
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
fun ScreenProfile() {
    Scaffold(
        Modifier,
        containerColor = NeutralWhite
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(start = 16.dp, end = 16.dp, top = 46.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomAvatar(modifier = Modifier, variant = 3, resId = R.drawable.my_photo)
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            SomeText(
                modifier = Modifier,
                text = "Anton Turitsyn",
                fontFamily = SfProDisplay,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                color = NeutralActive
            )
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            SomeText(
                modifier = Modifier,
                text = "+7 999 999-99-99",
                fontFamily = SfProDisplay,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = NeutralDisabled
            )
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MyIconButton(
                    modifier = Modifier
                        .size(width = 72.dp, height = 40.dp),
                    color = Color(0xFF9A41FE),
                    icon = painterResource(
                        id = R.drawable.ic_twitter
                    )
                )
                MyIconButton(
                    modifier = Modifier
                        .size(width = 72.dp, height = 40.dp),
                    color = BrandColorDefault,
                    icon = painterResource(
                        id = R.drawable.ic_inst
                    )
                )
                MyIconButton(
                    modifier = Modifier
                        .size(width = 72.dp, height = 40.dp),
                    color = BrandColorDefault,
                    icon = painterResource(
                        id = R.drawable.ic_linkedin
                    )
                )
                MyIconButton(
                    modifier = Modifier
                        .size(width = 72.dp, height = 40.dp),
                    color = BrandColorDefault,
                    icon = painterResource(
                        id = R.drawable.ic_facebook
                    )
                )
            }
        }
    }
}