package com.turitsynanton.android.wbtech.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.theme.NeutralActive
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
internal fun BottomIcon(isPressed: Boolean, title: String, icon: Int) {
    Column {

    }
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isPressed) {
            SomeText(
                modifier = Modifier.padding(vertical = 4.dp),
                text = title,
                fontFamily = SfProDisplay,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                color = NeutralActive
            )
            Icon(painter = painterResource(id = R.drawable.ic_dot), contentDescription = "")
        } else {
            Icon(painter = painterResource(id = icon), contentDescription = "")
        }
    }
}