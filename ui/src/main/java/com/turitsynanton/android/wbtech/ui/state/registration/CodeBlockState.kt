package com.turitsynanton.android.wbtech.ui.state.registration

internal data class CodeBlockState(
    val code: String = "",
    val codeStatusBar: String = "",
    val codeFieldStatus: Boolean = false,
    val timerField: String = "",
    val timerStatus: Boolean = false,
    val buttonRegistrationStatus: Boolean = false
)
