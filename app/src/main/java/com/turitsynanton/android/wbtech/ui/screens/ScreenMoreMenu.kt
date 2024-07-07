package com.turitsynanton.android.wbtech.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.navigation.NavigationMoreMenu
import com.turitsynanton.android.wbtech.navigation.TopBarMainScreens
import com.turitsynanton.android.wbtech.ui.components.MyMenuItem
import com.turitsynanton.android.wbtech.ui.components.MyProfileItem
import com.turitsynanton.android.wbtech.ui.items.SomeText

@Composable
fun ScreenMoreMenu(modifier: Modifier/*, onClick: () -> Unit*/) {
    Scaffold(
        topBar = {
            TopBarMainScreens(title = "Ещё", false)
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MyProfileItem(
                modifier = Modifier.clickable(
                    enabled = true,
                    onClickLabel = "",
                    role = null,
                    onClick = {
//                        onClick()
                    }
                ),
                name = "Anton Turitsyn",
                phone = "+7 999 999-99-99",
                avatar = R.drawable.my_photo
            )
            Spacer(modifier = Modifier.padding(bottom = 0.dp))
            MyMenuItem(modifier = Modifier.clickable(
                enabled = true,
                onClickLabel = "",
                role = null,
                onClick = {
//                        navController.popBackStack()
                }
            ), text = "Мои встречи", icon = R.drawable.ic_coffee)
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
            MyMenuItem(modifier = Modifier, text = "Тема", icon = R.drawable.ic_theme)
            MyMenuItem(modifier = Modifier, text = "Уведомления", icon = R.drawable.ic_notification)
            MyMenuItem(modifier = Modifier, text = "Безопасность", icon = R.drawable.ic_safety)
            MyMenuItem(modifier = Modifier, text = "Память и ресурсы", icon = R.drawable.ic_memory)
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFEDEDED))
            MyMenuItem(modifier = Modifier, text = "Помощь", icon = R.drawable.ic_help)
            MyMenuItem(modifier = Modifier, text = "Пригласи друга", icon = R.drawable.ic_invite)
        }
    }

}