package com.turitsynanton.android.wbtech.ui.screens.authscreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.turitsynanton.android.wbtech.MainViewModel
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.data.User
import com.turitsynanton.android.wbtech.ui.items.CustomAvatar
import com.turitsynanton.android.wbtech.ui.items.CustomPhoneField
import com.turitsynanton.android.wbtech.ui.items.MyFilledButton
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
fun ScreenAddName() {
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
                .padding(horizontal = 24.dp, vertical = 46.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomAvatar(
                modifier = Modifier
                    .padding(bottom = 32.dp),
                variant = 2,
                resId = R.drawable.icon_variant_user
            )
            TextFieldForAuth(hint = "Имя (обязательно)")
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
            TextFieldForAuth(hint = "Фамилия (опционально)")
            Spacer(modifier = Modifier.padding(bottom = 56.dp))
            MyFilledButton(modifier = Modifier
                .fillMaxWidth(),
                text = "Сохранить",
                color = Color(0xFF660EC8),
                enable = false,
                onClick = {}
            )
        }
    }
}

@Composable
fun TextFieldForAuth(hint: String) {
    var query: String by rememberSaveable { mutableStateOf("") }
    Row(
        Modifier
            .height(36.dp)
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(4.dp), color = Color(0xFFF7F7FC))
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = query,
            onValueChange = {
                query = it
            },
            enabled = true,
            textStyle = TextStyle(
                color = Color(0xFF666666),
                fontSize = 14.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight.Normal,
            ),
            decorationBox = { innerTextField ->
                if (query.isEmpty()) {
                    Text(
                        text = hint,
                        color = Color(0xFFADB5BD),
                        fontSize = 14.sp,
                        fontFamily = SfProDisplay,
                        fontWeight = FontWeight.Normal
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenAddNamePreview() {
    ScreenAddName()
}