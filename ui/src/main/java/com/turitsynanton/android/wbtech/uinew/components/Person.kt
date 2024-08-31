package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.items.Avatar
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.Tag
import com.turitsynanton.android.wbtech.uinew.utils.AvatarStyles
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle

@Composable
internal fun Person(
    modifier: Modifier,
    picture: Int,
    userName: String,
    tagInfo: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .width(104.dp)
            .clickable { onClick() }
    ) {
        Avatar(modifier = Modifier, avatarStyle = AvatarStyles.Medium, res = picture)
        Spacer(modifier = Modifier.padding(2.dp))
        SimpleTextField(
            modifier = Modifier,
            text = userName,
            fontFamily = SfProDisplay,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Tag(
            modifier = Modifier,
            text = tagInfo,
            style = TagsStyle.Minimize
        ) {
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonPreview() {
    Person(
        modifier = Modifier,
        picture = R.drawable.my_photo,
        userName = "Антон",
        tagInfo = "Разработка"
    ) {}
}