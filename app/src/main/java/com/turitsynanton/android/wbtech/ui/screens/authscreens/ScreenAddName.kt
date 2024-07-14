package com.turitsynanton.android.wbtech.ui.screens.authscreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.NavHostController
import com.turitsynanton.android.wbtech.MainViewModel
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.data.User
import com.turitsynanton.android.wbtech.navigation.topbars.TobBarAdditionalScreens
import com.turitsynanton.android.wbtech.ui.items.CustomAvatar
import com.turitsynanton.android.wbtech.ui.items.CustomPhoneField
import com.turitsynanton.android.wbtech.ui.items.MyFilledButton
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.theme.BrandColorDark
import com.turitsynanton.android.wbtech.ui.theme.NeutralDisabled
import com.turitsynanton.android.wbtech.ui.theme.NeutralSecondaryBG
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
fun ScreenAddName(navController: NavHostController, onClick: () -> Unit) {
    Scaffold(
        topBar = {
            TobBarAdditionalScreens("", navController, onBackPressed = {})
        }
    ) {
        var name by remember { mutableStateOf(User()) }
        var buttonEnable by remember { mutableStateOf(false) }
        val context = LocalContext.current

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
            TextFieldForAuth(hint = "Имя (обязательно)", user = User()) {
                name.name = it.name
                buttonEnable = name.name.length > 1
            }
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
            TextFieldForAuth(hint = "Фамилия (опционально)") {

            }
            Spacer(modifier = Modifier.padding(bottom = 56.dp))
            MyFilledButton(modifier = Modifier
                .fillMaxWidth(),
                text = "Сохранить",
                color = BrandColorDark,
                enable = buttonEnable,
                onClick = {
                    Toast.makeText(context, "!!!Успешно!!!", Toast.LENGTH_SHORT).show()
                    onClick()
                }
            )
        }
    }
}

@Composable
fun TextFieldForAuth(hint: String, user: User = User(), onNameEntered: (User) -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }
    Row(
        Modifier
            .height(36.dp)
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(4.dp),
                color = NeutralSecondaryBG
            )
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
                if (it.length > 1) {
                    onNameEntered(user.copy(name = it))
                }
            },
            enabled = true,
            textStyle = TextStyle(
                color = Color(0xFF666666),
                fontSize = 14.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight.Normal,
            ),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (query.isEmpty()) {
                        Text(
                            text = hint,
                            color = NeutralDisabled,
                            fontSize = 14.sp,
                            fontFamily = SfProDisplay,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                innerTextField()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenAddNamePreview() {
//    ScreenAddName()
}