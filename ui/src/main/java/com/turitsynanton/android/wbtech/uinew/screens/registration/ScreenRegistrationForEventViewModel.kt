package com.turitsynanton.android.wbtech.uinew.screens.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventDetailsUseCase
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.mapEventCardToUi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val CODE_LENGTH = 4
internal class ScreenRegistrationForEventViewModel(
    eventId: String,
    private val eventCardMapper: EventCardMapper,
    private val getEventDetailsUseCase: IGetEventDetailsUseCase
) : ViewModel() {
// TODO перенести все в usecase
    private val _eventDetails: MutableStateFlow<UiEventCard?> = MutableStateFlow(null)
    private val eventDetails: StateFlow<UiEventCard?> = _eventDetails.asStateFlow()

    private val _step: MutableStateFlow<Int> = MutableStateFlow(1)
    private val step: StateFlow<Int> = _step.asStateFlow()

    private val _nameQuery: MutableStateFlow<String> = MutableStateFlow("")
    private val nameQuery: StateFlow<String> = _nameQuery.asStateFlow()

    private val _phoneToQuery: MutableStateFlow<String> = MutableStateFlow("")
    private val phoneToQuery: StateFlow<String> = _phoneToQuery.asStateFlow()

    private val _fullPhoneNumber: MutableStateFlow<String> = MutableStateFlow("")
    private val fullPhoneNumber: StateFlow<String> = _fullPhoneNumber.asStateFlow()

    private val _codeQuery: MutableStateFlow<String> = MutableStateFlow("")
    private val codeQuery: StateFlow<String> = _codeQuery.asStateFlow()

    private val _getCodeToSend: MutableStateFlow<String> = MutableStateFlow("")
    private val getCodeToSend: StateFlow<String> = _getCodeToSend.asStateFlow()

    private val _errorCode: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val errorCode: StateFlow<Boolean> = _errorCode.asStateFlow()

    private val _getCodeStatusBar: MutableStateFlow<String> = MutableStateFlow("")
    private val getCodeStatusBar: StateFlow<String> = _getCodeStatusBar.asStateFlow()

    private val _buttonNextStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val buttonNextStatus: StateFlow<Boolean> = _buttonNextStatus.asStateFlow()

    private val _buttonSendCodeStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val buttonSendCodeStatus: StateFlow<Boolean> = _buttonSendCodeStatus.asStateFlow()

    private val _codeFieldError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val codeFieldError: StateFlow<Boolean> = _codeFieldError.asStateFlow()
    private val _buttonRegistrationStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val buttonRegistrationStatus: StateFlow<Boolean> = _buttonRegistrationStatus.asStateFlow()

    private val _timerField: MutableStateFlow<String> = MutableStateFlow("")
    private val timerField: StateFlow<String> = _timerField.asStateFlow()
    private val _timerStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val timerStatus: StateFlow<Boolean> = _timerStatus.asStateFlow()

    fun getEventDetailsFlow(): StateFlow<UiEventCard?> = eventDetails
    fun getFullPhoneNumberFlow(): StateFlow<String> = fullPhoneNumber
    fun getCodeStatusBarFlow(): StateFlow<String> = getCodeStatusBar
    fun getCodeToSendFlow(): StateFlow<String> = getCodeToSend
    fun getCodeFieldStatusFlow(): StateFlow<Boolean> = codeFieldError
    fun getTimerFieldFlow(): StateFlow<String> = timerField
    fun getTimerStatusFlow(): StateFlow<Boolean> = timerStatus
    fun setStepFlow(): StateFlow<Int> = step
    fun setPhoneFlow(): StateFlow<String> = phoneToQuery
    fun setCodeFlow(): StateFlow<String> = codeQuery
    fun setErrorCodeFlow(): StateFlow<Boolean> = errorCode
    fun setNameQueryFlow(): StateFlow<String> = nameQuery
    fun setButtonNextStatusFlow(): StateFlow<Boolean> = buttonNextStatus
    fun setButtonSendCodeStatusFlow(): StateFlow<Boolean> = buttonSendCodeStatus
    fun setButtonRegistrationStatusFlow(): StateFlow<Boolean> = buttonRegistrationStatus

    init {
        getEventDetails(eventId)
        getCodeStatusBar()
    }

    private fun getEventDetails(eventId: String) {
        viewModelScope.launch {
            getEventDetailsUseCase.execute(eventId).collect { event ->
                _eventDetails.update { event.mapEventCardToUi(eventCardMapper) }
            }
        }
    }

    fun setStep() {
        viewModelScope.launch {
//            TODO добавить обработку ошибки на >4
//            TODO добавить проверку на правильность кода и доступность кнопки
            _step.update { step ->
                step + 1
            }
            if (_step.value == 3) {
            setTimerField()
        }
        }
    }

    fun setPhoneQuery(phone: String) {
        viewModelScope.launch {
//            TODO добавить проверку на любые символы, кроме цифр
            val phoneToSave = phone.trim().take(10)
            Log.d("TAG", "setPhoneQuery: $phoneToSave")
            _phoneToQuery.update { phoneToSave }

            if (_phoneToQuery.value.length > 9) {
                _buttonSendCodeStatus.update { true }
            } else {
                _buttonSendCodeStatus.update { false }
            }
        }
    }

    fun sendCodeToPhone(code: String) {
        val phone = "$code${_phoneToQuery.value}"
        viewModelScope.launch {
            _fullPhoneNumber.update {
                phone
            }
        }
    }

    fun getCodeStatusBar() {
        viewModelScope.launch {
            if (_errorCode.value) {
                _getCodeStatusBar.update {
                    "Неправильный код"
                }
            } else {
                _getCodeStatusBar.update {
                    "Отправили код на ${_fullPhoneNumber.value}"
                }
            }
        }
    }
//      пока не используется
    private fun setPhoneToProfile(phone: String) {
        viewModelScope.launch {
            _phoneToQuery.update { phone }
        }
    }
//      пока не используется
    private fun compareCode() {
        viewModelScope.launch {

        }
    }

    fun registrationToEvent() {
        viewModelScope.launch {
            if (_codeQuery.value == "1234") {
                _getCodeStatusBar.update {
                    "Отправили код на ${_fullPhoneNumber.value}"
                }
                setStep()
            } else {
                _getCodeStatusBar.update {
                    "Неправильный код"
                }
                _codeFieldError.update { true }
                _buttonRegistrationStatus.update { false }
            }
        }
    }

    fun setNameQuery(name: String) {
        viewModelScope.launch {
//            TODO добавлять имя из профиля
            _nameQuery.update { name }

            if (_nameQuery.value.length > 1) {
                _buttonNextStatus.update { true }
            } else {
                _buttonNextStatus.update { false }
            }
        }
    }

    fun setCodeQuery(code: String) {
        viewModelScope.launch {
            _codeQuery.update { code }

            if (_codeQuery.value.length >= CODE_LENGTH) {
                _buttonRegistrationStatus.update { true }
            } else {
                _codeFieldError.update { false }
                _buttonRegistrationStatus.update { false }
            }
        }
    }

    fun setTimerField() {
        viewModelScope.launch {
            for (step in 10 downTo 0) {
                _timerField.update {
                    "Получить новый код через $step"
                }
                delay(1000L)
                _timerStatus.update { false }
            }
            _timerField.update {
                "Получить новый код"
            }
            _timerStatus.update { true }
        }
    }
}