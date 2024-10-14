package com.turitsynanton.android.wbtech.uinew.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.utils.ButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.SubscribeButtonStyle

@Composable
internal fun GradientButton(
    modifier: Modifier,
    text: String,
    buttonStyle: ButtonStyle,
    onClick: () -> Unit
) {
    if (buttonStyle.loading) {
        CircularProgressIndicator(
            modifier = modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    brush = buttonStyle.gradient
                )
                .padding(16.dp),
            color = buttonStyle.textColor
        )
    } else {
        SimpleTextField(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    brush = buttonStyle.gradient
                )
                .clickable(
                    enabled = buttonStyle.clickable
                ) {
                    onClick()
                }
                .padding(16.dp),
            text = text,
            fontFamily = SfProDisplay,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            color = buttonStyle.textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
internal fun FloatingCustomButton(
    modifier: Modifier,
    text: String,
    buttonStyle: ButtonStyle,
    onClick: () -> Unit
) {
    if (buttonStyle.loading) {
        CircularProgressIndicator(
            modifier = modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    brush = buttonStyle.gradient
                )
                .padding(16.dp),
            color = buttonStyle.textColor
        )
    } else {
        SimpleTextField(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    brush = buttonStyle.gradient
                )
                .clickable(
                    enabled = buttonStyle.clickable
                ) {
                    onClick()
                }
                .padding(16.dp),
            text = text,
            fontFamily = SfProDisplay,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            color = buttonStyle.textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
internal fun SubscribeButton(
    modifier: Modifier,
    style: SubscribeButtonStyle,
    onClick: () -> Unit
) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(12.dp))
        .background(
            brush = style.containerColor
        )
        .clickable {
            onClick()
        }
        .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = style.icon, contentDescription = "", tint = style.crossColor)
    }
}

@Composable
internal fun SocialButton(
    modifier: Modifier,
    icon: Painter,
    onClick: () -> Unit
) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(16.dp))
        .background(
            color = Color(0xFF9A10F0)
        )
        .clickable {
            onClick()
        }
        .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(painter = icon, contentDescription = "", tint = Color.White)
    }
}

@Composable
internal fun Exposed (
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Показать меню")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            Text("Скопировать", fontSize=18.sp, modifier = Modifier.padding(10.dp))
            Text("Вставить", fontSize=18.sp, modifier = Modifier.padding(10.dp))
            HorizontalDivider()
            Text("Настройки", fontSize=18.sp, modifier = Modifier.padding(10.dp))
        }
    }
}

@Preview(
    showBackground = true)
@Composable
private fun GradientButtonPreview() {
    Column {
        Exposed()
        GradientButton(
            modifier = Modifier,
            text = "Оплатить",
            buttonStyle = ButtonStyle.Secondary
        ) {
        }
        Spacer(modifier = Modifier.padding(18.dp))
        Row {
            SubscribeButton(modifier = Modifier, style = SubscribeButtonStyle.Done) {
            }
            Spacer(modifier = Modifier.padding(18.dp))
            SubscribeButton(modifier = Modifier, style = SubscribeButtonStyle.Default) {
            }
            Spacer(modifier = Modifier.padding(18.dp))
            SocialButton(modifier = Modifier, icon = painterResource(id = R.drawable.ic_habr)) {
            }
            Spacer(modifier = Modifier.padding(18.dp))
            SocialButton(modifier = Modifier, icon = painterResource(id = R.drawable.ic_telegram)) {
            }
        }
    }
}