package com.turitsynanton.android.wbtech.uinew.screens.registrationend

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.uinew.items.GradientButton
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.screens.registration.Country
import com.turitsynanton.android.wbtech.uinew.utils.ButtonStyle
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun ScreenFinishRegistration(
    modifier: Modifier = Modifier,
    eventId: String,
    screenFinishRegistrationViewModel: ScreenFinishRegistrationViewModel = koinViewModel(parameters = {
        parametersOf(
            eventId
        )
    }),
    onMyEventsClick: () -> Unit,
    onBackToEventsClick: () -> Unit
) {

    val event by screenFinishRegistrationViewModel.getEventDetailsFlow().collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.back_for_reg),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                event?.let { event ->
                    Title(
                        modifier = Modifier,
                        title = R.string.successful_registration,
                        event = event
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
                Column(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    SimpleTextField(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .clickable { },
                        text = stringResource(id = R.string.my_events),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF9A41FE)
                    )
                    Spacer(modifier = Modifier.padding(12.dp))
                    GradientButton(
                        modifier = Modifier,
                        text = stringResource(R.string.find_other_events),
                        buttonStyle = ButtonStyle.Enable
                    ) {
                        onBackToEventsClick()
                    }
                }
            }
        }
    }
}

@Composable
internal fun Title(
    modifier: Modifier,
    title: Int,
    event: UiEventCard
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SimpleTextField(
            modifier = Modifier,
            text = stringResource(title),
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000),
            lineHeight = 40.sp
        )
        Spacer(modifier = Modifier.padding(7.dp))
        SimpleTextField(
            modifier = Modifier,
            text = "${event.name} · ${event.date} · ${event.address}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.padding(12.dp))
    }
}

data class Country(val name: String, val code: String, val flag: Int)