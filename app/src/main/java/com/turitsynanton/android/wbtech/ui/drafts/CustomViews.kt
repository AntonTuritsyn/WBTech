package com.turitsynanton.android.wbtech.ui.drafts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.wbtech.data.User
import com.turitsynanton.android.wbtech.ui.items.CodeCustomTextField
import com.turitsynanton.android.wbtech.ui.items.CustomPhoneField

@Composable
fun CustomViews() {
    var otpValue by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.padding(vertical = 20.dp))
        CodeCustomTextField(
            Modifier,
            otpValue,
            4,
            onTextChange = { value ->
                otpValue = value
            }
        )
        Spacer(Modifier.padding(vertical = 16.dp))
        CustomPhoneField(Modifier.padding(horizontal = 24.dp), user = User(), onPhoneEntered = {})
    }
}