package com.turitsynanton.android.wbtech.uinew.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateTagsForEvent
import com.turitsynanton.android.wbtech.data.mocks.generateUsersList
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataUser
import com.turitsynanton.android.wbtech.uinew.components.Person
import com.turitsynanton.android.wbtech.uinew.components.TopBar

@Composable
internal fun ScreenParticipants(
    modifier: Modifier = Modifier,
    participants: List<DataUser>,
    onBackClick: () -> Unit,
    onUserClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                title = "Пойдут на встречу",
                needActions = false,
                onShareClick = {}) {
                onBackClick()
            }
        }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(top = 32.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(participants.chunked(3)) { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    for (participant in rowItems) {
                        Person(
                            modifier = Modifier,
                            picture = R.drawable.my_photo,
                            userName = participant.name,
                            tagInfo = generateTagsForEvent().first().content
                        ) {
                            onUserClick()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenParticipantsPreview() {
    ScreenParticipants(
        modifier = Modifier,
        participants = generateUsersList(),
        onBackClick = {}
    ) {}
}