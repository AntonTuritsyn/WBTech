package com.turitsynanton.android.wbtech.uinew.screens.registration

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.uinew.components.CountryDropDown
import com.turitsynanton.android.wbtech.uinew.components.PhoneField
import com.turitsynanton.android.wbtech.uinew.items.ComplexTextField
import com.turitsynanton.android.wbtech.uinew.items.GradientButton
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.utils.ButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.TextFieldStyle
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun ScreenRegistrationForEvent(
    modifier: Modifier = Modifier,
    eventId: String,
    screenRegistrationForEventViewModel: ScreenRegistrationForEventViewModel = koinViewModel(
        parameters = {
            parametersOf(
                eventId
            )
        }),
    selectedCountry: Country,
    onCloseClick: () -> Unit,
    onBackToEventsClick: () -> Unit
) {
//    TODO в state
    val event by screenRegistrationForEventViewModel.getEventDetailsFlow()
        .collectAsStateWithLifecycle()
    val step by screenRegistrationForEventViewModel.setStepFlow().collectAsStateWithLifecycle()
    val name by screenRegistrationForEventViewModel.setNameQueryFlow().collectAsStateWithLifecycle()
    val buttonNext by screenRegistrationForEventViewModel.setButtonNextStatusFlow()
        .collectAsStateWithLifecycle()
    val phone by screenRegistrationForEventViewModel.setPhoneFlow().collectAsStateWithLifecycle()
    val phoneButton by screenRegistrationForEventViewModel.setButtonSendCodeStatusFlow()
        .collectAsStateWithLifecycle()
    val code by screenRegistrationForEventViewModel.setCodeFlow().collectAsStateWithLifecycle()
    val codeStatusBar by screenRegistrationForEventViewModel.getCodeStatusBarFlow()
        .collectAsStateWithLifecycle()
    val codeFieldBar by screenRegistrationForEventViewModel.getCodeFieldStatusFlow()
        .collectAsStateWithLifecycle()
    val registrationButtonStatus by screenRegistrationForEventViewModel.setButtonRegistrationStatusFlow()
        .collectAsStateWithLifecycle()
    val timerField by screenRegistrationForEventViewModel.getTimerFieldFlow()
        .collectAsStateWithLifecycle()
    val timerStatus by screenRegistrationForEventViewModel.getTimerStatusFlow()
        .collectAsStateWithLifecycle()


    Scaffold(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()

        ) {
            when(step) {
                4 -> {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.back_for_reg),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                else -> {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .clickable(
                                    interactionSource = remember {
                                        MutableInteractionSource()
                                    },
                                    indication = null
                                ) { onCloseClick() },
                            painter = painterResource(id = R.drawable.ic_cross),
                            contentDescription = null
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(16.dp)
            ) {
                event?.let { event ->
                    Title(
                        modifier = Modifier,
                        title = if (step == 4) {
                            R.string.successful_registration
                        } else {
                            R.string.signup_to_event
                        },
                        event = event,
                        selectedCountry = selectedCountry
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
                Column(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    when (step) {
//                    add name
                        1 -> {
                            ComplexTextField(
                                hint = stringResource(R.string.hint_name),
                                query = name,
                                onQueryChanged = {
                                    screenRegistrationForEventViewModel.setNameQuery(
                                        it
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Sentences,
                                    keyboardType = KeyboardType.Text
                                ),
                                textFieldStyle = TextFieldStyle.Filled
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            GradientButton(
                                modifier = Modifier,
                                text = stringResource(R.string.next_button),
                                buttonStyle = if (!buttonNext) {
                                    ButtonStyle.Disable
                                } else {
                                    ButtonStyle.Enable
                                }
                            ) {
                                screenRegistrationForEventViewModel.setStep()
                            }
                        }
//                    add phone
                        2 -> {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

//                            TODO ОБЯЗАТЕЛЬНО ДОРАБОТАТЬ ВСЮ ЛОГИКУ ЧЕРЕЗ VM
//__________________________________________________________________________________________________________
                                CountryDropDown(
                                    modifier = Modifier,
                                    selectedCountry = selectedCountry
                                )

                                PhoneField(modifier = Modifier, number = phone) {
                                    Log.d("TAG", "phone: $it")
                                    screenRegistrationForEventViewModel.setPhoneQuery(it)
                                }
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            GradientButton(
                                modifier = Modifier,
                                text = stringResource(R.string.get_code),
                                buttonStyle = if (!phoneButton) {
                                    ButtonStyle.Disable
                                } else {
                                    ButtonStyle.Enable
                                }
                            ) {
                                screenRegistrationForEventViewModel.apply {
                                    sendCodeToPhone("${selectedCountry.code}")
                                    screenRegistrationForEventViewModel.getCodeStatusBar()
                                    setStep()
                                }
                            }
                        }
//                    enter code
                        3 -> {
                            ComplexTextField(
                                modifier = Modifier,
                                hint = stringResource(R.string.code_plaseholder),
                                query = code,
                                onQueryChanged = {
                                    screenRegistrationForEventViewModel.setCodeQuery(
                                        it
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                textFieldStyle = if (!codeFieldBar) {
                                    TextFieldStyle.Filled
                                } else {
                                    TextFieldStyle.Error
                                }
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
                            SimpleTextField(
                                modifier = Modifier,
                                text = codeStatusBar,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF76778E)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            SimpleTextField(
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .clickable(
                                        enabled = timerStatus,
                                    ) { },
                                text = timerField,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (!timerStatus) {
                                    Color(0xFF76778E)
                                } else {
                                    Color(0xFF9A10F0)
                                }
                            )
                            Spacer(modifier = Modifier.padding(12.dp))
                            GradientButton(
                                modifier = Modifier,
                                text = stringResource(R.string.registration),
                                buttonStyle = if (!registrationButtonStatus) {
                                    ButtonStyle.Disable
                                } else {
                                    ButtonStyle.Enable
                                }
                            ) {
                                screenRegistrationForEventViewModel.registrationToEvent()
                            }
                        }

                        4 -> {
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
    }
}

@Composable
internal fun Title(
    modifier: Modifier,
    title: Int,
    event: UiEventCard,
    selectedCountry: Country
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun Preview() {
    ScreenRegistrationForEvent(
        modifier = Modifier,
        eventId = "1",
        selectedCountry = Country("Russia", "+7", R.drawable.flag_russia),
        onCloseClick = {}
    ) {

    }
}