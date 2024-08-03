package com.turitsynanton.android.wbtech.ui.screens.authscreens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.storage.models.DataUser
import com.turitsynanton.android.wbtech.ui.components.TextFieldForAuth
import com.turitsynanton.android.wbtech.ui.items.CustomAvatar
import com.turitsynanton.android.wbtech.ui.items.MyFilledButton
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.AuthViewModel
import com.turitsynanton.android.wbtech.ui.theme.BrandColorDark
import com.turitsynanton.android.wbtech.ui.topbars.TobBarAdditionalScreens
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun ScreenAddName(
    authViewModel: AuthViewModel = koinViewModel(),
    navController: NavHostController, onClick: () -> Unit) {
    Scaffold(
        topBar = {
            TobBarAdditionalScreens("", navController, onBackPressed = {})
        }
    ) {
//        имя, фамилия будут пернесены в вм
        var name by remember { mutableStateOf("") }
        var surname by remember { mutableStateOf("") }
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
            TextFieldForAuth(hint = "Имя (обязательно)", user = DataUser()) {
                name = it.name
                buttonEnable = name.length > 1
            }
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
            TextFieldForAuth(hint = "Фамилия (опционально)", user = DataUser()) {
                surname = it.surname
            }
            Spacer(modifier = Modifier.padding(bottom = 56.dp))
            MyFilledButton(modifier = Modifier
                .fillMaxWidth(),
                text = "Сохранить",
                color = BrandColorDark,
                enable = buttonEnable,
                onClick = {
                    Toast.makeText(context, "!!!Успешно!!!", Toast.LENGTH_SHORT).show()
                    /*authViewModel.saveUser(newUser = User(
                        name = name,
                        surname = surname
                    )
                    )*/
                    onClick()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenAddNamePreview() {
//    ScreenAddName()
}