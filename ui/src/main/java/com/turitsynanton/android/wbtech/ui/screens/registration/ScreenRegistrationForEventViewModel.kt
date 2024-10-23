package com.turitsynanton.android.wbtech.ui.screens.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.registration.ISetRegistrationStepUseCase
import com.turitsynanton.android.wbtech.domain.usecases.registration.code.ISetCodeQueryInteractor
import com.turitsynanton.android.wbtech.domain.usecases.registration.code.ISetTimerFieldInteractor
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.mapEventCardToUi
import com.turitsynanton.android.wbtech.ui.state.eventdetails.ScreenEventDetailsMainState
import com.turitsynanton.android.wbtech.ui.state.registration.CodeBlockState
import com.turitsynanton.android.wbtech.ui.state.registration.MainRegistrationState
import com.turitsynanton.android.wbtech.ui.state.registration.NameBlockState
import com.turitsynanton.android.wbtech.ui.state.registration.PhoneBlockState
import com.turitsynanton.android.wbtech.ui.utils.ResourceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val CODE_LENGTH = 4
private const val TAG = "ScreenRegistrationForEventViewModel"

internal class ScreenRegistrationForEventViewModel(
    eventId: String,
    private val eventCardMapper: EventCardMapper,
    private val resourceProvider: ResourceProvider,
    private val getEventDetailsUseCase: IGetEventDetailsUseCase,
    private val setRegistrationStep: ISetRegistrationStepUseCase,
    private val setTimerField: ISetTimerFieldInteractor,
    private val setCodeQuery: ISetCodeQueryInteractor
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
    private val buttonRegistrationStatus: StateFlow<Boolean> =
        _buttonRegistrationStatus.asStateFlow()

    private val _timerField: MutableStateFlow<String> = MutableStateFlow("")
    private val timerField: StateFlow<String> = _timerField.asStateFlow()

    private val _timerStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val timerStatus: StateFlow<Boolean> = _timerStatus.asStateFlow()

    val screenMainInfoState: StateFlow<MainRegistrationState> = combine(
        getEventDetailsFlow(),
        setStepFlow()
    ) { flows ->
        MainRegistrationState(
            event = flows.getOrNull(0) as UiEventCard,
            step = flows.getOrNull(1) as Int
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, MainRegistrationState())

    val screenNameState: StateFlow<NameBlockState> = combine(
        setNameQueryFlow(),
        setButtonNextStatusFlow()
    ) { flows ->
        NameBlockState(
            name = flows.getOrNull(0) as String,
            buttonNextStatus = flows.getOrNull(1) as Boolean
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, NameBlockState())

    val screenPhoneState: StateFlow<PhoneBlockState> = combine(
        setPhoneFlow(),
        setButtonSendCodeStatusFlow()
    ) { flows ->
        PhoneBlockState(
            phone = flows.getOrNull(0) as String,
            buttonSendCodeStatus = flows.getOrNull(1) as Boolean
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, PhoneBlockState())

    val screenCodeState: StateFlow<CodeBlockState> = combine(
        setCodeFlow(),
        getCodeStatusBarFlow(),
        getCodeFieldStatusFlow(),
        getTimerFieldFlow(),
        getTimerStatusFlow(),
        setButtonRegistrationStatusFlow()
    ) { flows ->
        CodeBlockState(
            code = flows.getOrNull(0) as String,
            codeStatusBar = flows.getOrNull(1) as String,
            codeFieldStatus = flows.getOrNull(2) as Boolean,
            timerField = flows.getOrNull(3) as String,
            timerStatus = flows.getOrNull(4) as Boolean,
            buttonRegistrationStatus = flows.getOrNull(5) as Boolean
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, CodeBlockState())

    init {
        getEventDetails(eventId)
        getCodeStatusBar()
    }

    fun setStep() {
        viewModelScope.launch {
            setRegistrationStep.execute(_step.value).collect { step ->
                Log.d(TAG, "setStep: $step")
                _step.update { step }
            }
//            TODO добавить обработку ошибки на >4
//            TODO добавить проверку на правильность кода и доступность кнопки
        }
        if (_step.value == 3) {
            Log.d(TAG, "ВЫЗЫВАЕТСЯ 3")
            setTimerField()
            setTimerStatus()
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
        val phone = "$code ${_phoneToQuery.value}"
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
            setCodeQuery.observeCodeQueryFlow(code).collect { query ->
                _codeQuery.update { query }
            }
        }
        viewModelScope.launch {
            setCodeQuery.observeButtonRegistrationStatusFlow().collect { status ->
                _buttonRegistrationStatus.update { status }
            }
        }
        viewModelScope.launch {
            setCodeQuery.observeCodeFieldErrorFlow().collect { status ->
                _codeFieldError.update { status }
            }
        }
    }

    fun registrationToEvent() {
        viewModelScope.launch {
            setCodeQuery.observeCodeStatusBarFlow().collect { status ->
                _getCodeStatusBar.update { status }
                /*if (_codeQuery.value == "1234") {
                _getCodeStatusBar.update {
                    resourceProvider.getString(R.string.send_code_to_num) + _fullPhoneNumber.value
                }
                setStep()
            } else {
                _getCodeStatusBar.update {
                    "Неправильный код"
                }
                _codeFieldError.update { true }
                _buttonRegistrationStatus.update { false }
            }*/
            }
            viewModelScope.launch {
                setCodeQuery.observeButtonRegistrationStatusFlow().collect { status ->
                    _buttonRegistrationStatus.update { status }
                }
            }
            viewModelScope.launch {
                setCodeQuery.observeCodeFieldErrorFlow().collect { status ->
                    _codeFieldError.update { status }
                }
            }
        }
        setCodeQuery.setStepIfRightCode {
            setStep()
        }
    }

    private fun getEventDetailsFlow(): StateFlow<UiEventCard?> = eventDetails
    private fun getFullPhoneNumberFlow(): StateFlow<String> = fullPhoneNumber
    private fun getCodeStatusBarFlow(): StateFlow<String> = getCodeStatusBar
    private fun getCodeToSendFlow(): StateFlow<String> = getCodeToSend
    private fun getCodeFieldStatusFlow(): StateFlow<Boolean> = codeFieldError
    private fun getTimerFieldFlow(): StateFlow<String> = timerField
    private fun getTimerStatusFlow(): StateFlow<Boolean> = timerStatus
    private fun setStepFlow(): StateFlow<Int> = step
    private fun setPhoneFlow(): StateFlow<String> = phoneToQuery
    private fun setCodeFlow(): StateFlow<String> = codeQuery
    private fun setErrorCodeFlow(): StateFlow<Boolean> = errorCode
    private fun setNameQueryFlow(): StateFlow<String> = nameQuery
    private fun setButtonNextStatusFlow(): StateFlow<Boolean> = buttonNextStatus
    private fun setButtonSendCodeStatusFlow(): StateFlow<Boolean> = buttonSendCodeStatus
    private fun setButtonRegistrationStatusFlow(): StateFlow<Boolean> = buttonRegistrationStatus

    private fun getEventDetails(eventId: String) {
        viewModelScope.launch {
            getEventDetailsUseCase.execute(eventId).collect { event ->
                _eventDetails.update { event.mapEventCardToUi(eventCardMapper) }
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

    private fun setTimerField() {
        viewModelScope.launch {
            setTimerField.observeTimerFieldFlow().collect { field ->
                _timerField.update { field }
            }
        }
    }

    private fun setTimerStatus() {
        viewModelScope.launch {
            setTimerField.observeTimerStatusFlow().collect { status ->
                _timerStatus.update { status }
            }
        }
    }
}