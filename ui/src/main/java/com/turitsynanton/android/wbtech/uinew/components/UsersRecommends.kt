package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateTagsForEvent
import com.turitsynanton.android.wbtech.data.mocks.generateUsersList
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataTag
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataUser
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField

@Composable
internal fun UsersRecommends(
    modifier: Modifier,
    usersList: List<DataUser>,
    userTag: List<DataTag>,
    onUserClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SimpleTextField(
            modifier = Modifier,
            text = stringResource(id = R.string.you_migth_know),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(usersList.size) { index ->
                val generatedUserTag: List<DataTag> = generateTagsForEvent(1, 1)
                Person(
                    modifier = Modifier,
                    picture = R.drawable.my_photo,
                    userName = usersList[index].name,
                    tagInfo = generatedUserTag.first().content
                ) {
                    onUserClick()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersRecommendsPreview() {
    UsersRecommends(
        modifier = Modifier,
        usersList = generateUsersList(),
        userTag = listOf()
    ) {}
}