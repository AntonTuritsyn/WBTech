package com.turitsynanton.android.wbtech.ui.topbars

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.theme.NeutralActive
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBarMainScreens(title: String, mayAdd: Boolean) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    TopAppBar(
        modifier = Modifier.padding(horizontal = 6.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Unspecified,
            titleContentColor = NeutralActive
        ),
        title = {
            SomeText(
                modifier = Modifier,
                text = title,
                fontFamily = SfProDisplay,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                color = NeutralActive
            )
        },
        actions = {
            if (mayAdd == true) {
                IconButton(
                    onClick = {
                        /*do something */
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = ""
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
    )
}