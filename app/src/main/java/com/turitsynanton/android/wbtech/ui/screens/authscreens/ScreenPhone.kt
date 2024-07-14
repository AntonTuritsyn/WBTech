package com.turitsynanton.android.wbtech.ui.screens.authscreens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.turitsynanton.android.wbtech.MainViewModel
import com.turitsynanton.android.wbtech.data.User
import com.turitsynanton.android.wbtech.navigation.Navigation
import com.turitsynanton.android.wbtech.ui.items.CustomPhoneField
import com.turitsynanton.android.wbtech.ui.items.MyFilledButton
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
fun ScreenPhone(
    viewModel: MainViewModel = viewModel(),
    navController: NavHostController
) {
    val maxPhoneNumberLength = 10
    Scaffold(
        topBar = {
//            TopBarMainScreens(title = "Встречи", true)
        }
    ) {
        var userNum by remember { mutableStateOf(User()) }
        var buttonEnable by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(it)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SomeText(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                text = "Введите номер телефона",
                fontFamily = SfProDisplay,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                color = Color(0xFF29183B)
            )
            SomeText(
                modifier = Modifier,
                text = "Мы вышлем код подтверждения\nна указаный номер",
                fontFamily = SfProDisplay,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = Color(0xFF29183B),
                textAlign = TextAlign.Center,
                lineHeight = LocalTextStyle.current.copy(lineHeight = 24.sp)
            )
            Spacer(modifier = Modifier.padding(bottom = 48.dp))
            CustomPhoneField(modifier = Modifier, user = User(), onPhoneEntered = {
                val phoneNumber = it.phone
                userNum.phone = phoneNumber
                buttonEnable = userNum.phone.length == maxPhoneNumberLength
            })
            Spacer(modifier = Modifier.padding(bottom = 68.dp))
            MyFilledButton(modifier = Modifier
                .fillMaxWidth(),
                text = "Продолжить",
                color = Color(0xFF660EC8),
                enable = buttonEnable,
                onClick = {
                    navController.navigate("${Navigation.ScreenCode.route}/${userNum.phone}")
//                    viewModel.updateUser(userNum)
                    Log.d("TAG", "phone ${userNum.phone}")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    val navController = rememberNavController()
    ScreenPhone(navController = navController)
}