package com.turitsynanton.android.wbtech.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.data.storage.models.User
import com.turitsynanton.android.wbtech.ui.theme.NeutralDisabled
import com.turitsynanton.android.wbtech.ui.theme.NeutralSecondaryBG
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

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