package com.turitsynanton.android.wbtech.domain.usecases.auth

import com.turitsynanton.android.wbtech.domain.usecases.stubs.SendCodeUseCaseStub
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SendCodeUseCaseTest {
    private lateinit var sendCodeUseCaseStub: SendCodeUseCaseStub

    @BeforeEach
    fun setUp() {
        sendCodeUseCaseStub = SendCodeUseCaseStub()
    }

    @Test
    fun `test execute returns true for valid code`() {
        val code = "123456"
        sendCodeUseCaseStub.returnValue = true

        val result = sendCodeUseCaseStub.execute(code)

        assertTrue(result)
        assertEquals(code, sendCodeUseCaseStub.codeForTest)
    }

    @Test
    fun `test execute returns false for invalid code`() {
        val code = "invalid_code"
        sendCodeUseCaseStub.returnValue = false

        val result = sendCodeUseCaseStub.execute(code)

        assertTrue(!result)
        assertEquals(code, sendCodeUseCaseStub.codeForTest)
    }
}