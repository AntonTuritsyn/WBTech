package com.turitsynanton.android.wbtech.ui.screens.registration

import android.util.Log
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.ui.components.CountryDropDown
import com.turitsynanton.android.wbtech.ui.components.PhoneField
import com.turitsynanton.android.wbtech.ui.items.ComplexTextField
import com.turitsynanton.android.wbtech.ui.items.GradientButton
import com.turitsynanton.android.wbtech.ui.items.SimpleTextField
import com.turitsynanton.android.wbtech.ui.utils.ButtonStyle
import com.turitsynanton.android.wbtech.ui.utils.TextFieldStyle
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

interface ScreenRegistrationActions {
    fun onCloseClick()
    fun onFinishRegistrationClick(eventId: String)
    fun onBackToEventsClick()
}

@Composable
internal fun ScreenRegistrationForEvent(
    modifier: Modifier = Modifier,
    eventId: String,
    screenRegistrationForEventViewModel: ScreenRegistrationForEventViewModel = koinViewModel(
        parameters = {
            parametersOf(
                eventId
            )
        }
    ),
    selectedCountry: Country,
    /*onCloseClick: () -> Unit,
    onFinishRegistrationClick: (String) -> Unit,
    onBackToEventsClick: () -> Unit,*/
    actions: ScreenRegistrationActions
) {
//    TODO в state

    val screenMainInfoState by screenRegistrationForEventViewModel.screenMainInfoState.collectAsStateWithLifecycle()
    val screenNameState by screenRegistrationForEventViewModel.screenNameState.collectAsStateWithLifecycle()
    val screenPhoneState by screenRegistrationForEventViewModel.screenPhoneState.collectAsStateWithLifecycle()
    val screenCodeState by screenRegistrationForEventViewModel.screenCodeState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()

        ) {
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
                        ) { /*onCloseClick()*/actions.onCloseClick() },
                    painter = painterResource(id = R.drawable.ic_cross),
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                screenMainInfoState.event?.let { event ->
                    Title(
                        modifier = Modifier,
                        title = R.string.signup_to_event,
                        event = event,
                        selectedCountry = selectedCountry
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
                Column(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    when (screenMainInfoState.step) {
//                    add name
                        1 -> {
                            ComplexTextField(
                                hint = stringResource(R.string.hint_name),
                                query = screenNameState.name,
                                onQueryChanged = { name ->
                                    screenRegistrationForEventViewModel.setNameQuery(
                                        name
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
                                buttonStyle = if (!screenNameState.buttonNextStatus) {
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
// __________________________________________________________________________________________________________
                                CountryDropDown(
                                    modifier = Modifier,
                                    selectedCountry = selectedCountry
                                )

                                PhoneField(
                                    modifier = Modifier,
                                    number = screenPhoneState.phone
                                ) { phone ->
                                    Log.d("TAG", "phone: $phone")
                                    screenRegistrationForEventViewModel.setPhoneQuery(phone)
                                }
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            GradientButton(
                                modifier = Modifier,
                                text = stringResource(R.string.get_code),
                                buttonStyle = if (!screenPhoneState.buttonSendCodeStatus) {
                                    ButtonStyle.Disable
                                } else {
                                    ButtonStyle.Enable
                                }
                            ) {
                                screenRegistrationForEventViewModel.apply {
                                    sendCodeToPhone(selectedCountry.code)
                                    getCodeStatusBar()
                                    setStep()
                                }
                            }
                        }
//                    enter code
                        3 -> {
                            ComplexTextField(
                                modifier = Modifier,
                                hint = stringResource(R.string.code_plaseholder),
                                query = screenCodeState.code,
                                onQueryChanged = { query ->
                                    screenRegistrationForEventViewModel.setCodeQuery(
                                        query
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                textFieldStyle = if (!screenCodeState.codeFieldStatus) {
                                    TextFieldStyle.Filled
                                } else {
                                    TextFieldStyle.Error
                                }
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
                            SimpleTextField(
                                modifier = Modifier,
                                text = screenCodeState.codeStatusBar,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF76778E)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            SimpleTextField(
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .clickable(
                                        enabled = screenCodeState.timerStatus,
                                    ) {
                                        screenRegistrationForEventViewModel.apply {
                                            sendCodeToPhone(selectedCountry.code)
                                            getCodeStatusBar()
                                            setTimerField()
                                        }
                                    },
                                text = screenCodeState.timerField,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (!screenCodeState.timerStatus) {
                                    Color(0xFF76778E)
                                } else {
                                    Color(0xFF9A10F0)
                                }
                            )
                            Spacer(modifier = Modifier.padding(12.dp))
                            GradientButton(
                                modifier = Modifier,
                                text = stringResource(R.string.registration),
                                buttonStyle = if (!screenCodeState.buttonRegistrationStatus) {
                                    ButtonStyle.Disable
                                } else {
                                    ButtonStyle.Enable
                                }
                            ) {
                                if (screenCodeState.isCodeRight) {
                                    /*onFinishRegistrationClick(eventId)*/
                                    actions.onFinishRegistrationClick(eventId)
                                } else {
                                    screenRegistrationForEventViewModel.registrationToEvent()
                                }
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
