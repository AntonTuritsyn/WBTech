package com.turitsynanton.android.wbtech.ui.screens.authscreens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.turitsynanton.android.wbtech.navigation.Navigation
import com.turitsynanton.android.wbtech.navigation.topbars.TobBarAdditionalScreens
import com.turitsynanton.android.wbtech.ui.items.CodeCustomTextField
import com.turitsynanton.android.wbtech.ui.items.MyTextButton
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
fun ScreenCode(
    viewModel: MainViewModel = viewModel(),
    navController: NavHostController,
    phoneNum: String
) {
    val user by viewModel.user.collectAsState()
    Scaffold(
        topBar = {
            TobBarAdditionalScreens("", navController, onBackPressed = {})
        }
    ) {
        var otpValue by remember {
            mutableStateOf("")
        }
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
                text = "Введите код",
                fontFamily = SfProDisplay,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                color = Color(0xFF29183B)
            )
            SomeText(
                modifier = Modifier,
                text = "Отправили код на номер\n +7 $phoneNum", // пока что мерзкий хардкод((
                fontFamily = SfProDisplay,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = Color(0xFF29183B),
                textAlign = TextAlign.Center,
                lineHeight = LocalTextStyle.current.copy(lineHeight = 24.sp)
            )
            Spacer(modifier = Modifier.padding(bottom = 48.dp))
            CodeCustomTextField(otpText = otpValue, onTextChange = { value ->
                otpValue = value
            })
            Spacer(modifier = Modifier.padding(bottom = 68.dp))
            MyTextButton(modifier = Modifier, text = "Запросить код повторно") {
                navController.navigate(route = Navigation.ScreenAddName.route)
                Log.d("TAG", "phoneCode ${user?.phone}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenCodePreview() {
    val navController = rememberNavController()
    ScreenCode(
        navController = navController,
        phoneNum = ""
    )
}