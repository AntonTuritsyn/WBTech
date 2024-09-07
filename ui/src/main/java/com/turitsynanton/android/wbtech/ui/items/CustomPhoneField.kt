package com.turitsynanton.android.wbtech.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.ui.theme.NeutralDisabled
import com.turitsynanton.android.wbtech.ui.theme.NeutralSecondaryBG
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.utils.PhoneVisualTransformation

@Composable
internal fun CustomPhoneField(modifier: Modifier, user: DomainUser, onPhoneEntered: (DomainUser) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }
    val flag = painterResource(id = R.drawable.flag)
    val countryCode = "+7"
    val maxPhoneNumberLength = 10
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .background(Color.Transparent)
            .height(36.dp)
    ) {
        Row(
            modifier = Modifier
                .width(57.dp)
                .fillMaxHeight()
                .background(
                    shape = RoundedCornerShape(4.dp),
                    color = NeutralSecondaryBG
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .size(16.dp),
                painter = flag,
                contentScale = ContentScale.Crop,
                contentDescription = "",
            )
            SomeText(
                modifier = Modifier,
                text = countryCode,
                fontFamily = SfProDisplay,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                color = NeutralDisabled
            )
        }
        BasicTextField(
            value = text,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                when {
                    it.length <= maxPhoneNumberLength -> text = it
                    it.length == maxPhoneNumberLength -> onPhoneEntered(user.copy(phone = it))
                }
            },
            visualTransformation = PhoneVisualTransformation(),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(
                    NeutralSecondaryBG,
                    shape = RoundedCornerShape(4.dp)
                ),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isEmpty())
                        SomeText(
                            modifier = Modifier,
                            text = "000 000-00-00",
                            fontFamily = SfProDisplay,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Normal,
                            color = NeutralDisabled
                        )
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CustomPhoneField(Modifier, user = DomainUser(), onPhoneEntered = {})
}