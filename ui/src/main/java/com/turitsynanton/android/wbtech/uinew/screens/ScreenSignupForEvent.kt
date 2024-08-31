package com.turitsynanton.android.wbtech.uinew.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField

@Composable
internal fun ScreenSignupForEvent(
    modifier: Modifier,
    event: DataEvent, // TODO: DomainEvent
    onCloseClick: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(16.dp)
        ) {
            Title(modifier = Modifier, event = event)
        }
    }
}

@Composable
internal fun Title(
    modifier: Modifier,
    event: DataEvent // TODO: DomainEvent
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SimpleTextField(
            modifier = Modifier,
            text = stringResource(R.string.signup_to_event),
            fontSize = 50.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000),
            lineHeight = 40.sp
        )
        Spacer(modifier = Modifier.padding(7.dp))
        SimpleTextField(
            modifier = Modifier,
            text = "${event.name} · ${event.date} · ${event.city}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF000000)
        )
        
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun Preview() {
    ScreenSignupForEvent(
        modifier = Modifier,
        event = generateEvents().first(),
        onCloseClick = {}
    )
}