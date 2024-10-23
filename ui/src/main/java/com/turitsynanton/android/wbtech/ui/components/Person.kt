package com.turitsynanton.android.wbtech.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.models.UiPersonCard
import com.turitsynanton.android.wbtech.models.UiTag
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.ui.items.Avatar
import com.turitsynanton.android.wbtech.ui.items.SimpleTextField
import com.turitsynanton.android.wbtech.ui.items.Tag
import com.turitsynanton.android.wbtech.ui.utils.AvatarStyles
import com.turitsynanton.android.wbtech.ui.utils.TagsStyle

@Composable
internal fun Person(
    modifier: Modifier,
    user: UiPersonCard,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .width(104.dp)
            .clickable { onClick() }
    ) {
        Avatar(modifier = Modifier, avatarStyle = AvatarStyles.Medium, user = user)
        Spacer(modifier = Modifier.padding(2.dp))
        SimpleTextField(
            modifier = Modifier,
            text = user.name,
            fontFamily = SfProDisplay,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Tag(
            modifier = Modifier,
            text = user.tag.content,
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
        user = UiPersonCard(
            "test",
            "Anton",
            UiTag("test", "Проджект менеджмент"),
            "https://i.pinimg.com/736x/c6/0e/c9/c60ec93fb07de3431b475c943272632d.jpg"
        ),
        onClick = {})
}
