package com.turitsynanton.android.wbtech.domain.usecases.auth

import com.turitsynanton.android.wbtech.domain.usecases.stubs.SavePhoneNumUseCaseStub
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SavePhoneNumUseCaseTest {

    private lateinit var savePhoneNumUseCaseStub: SavePhoneNumUseCaseStub

    @BeforeEach
    fun setUp() {
        savePhoneNumUseCaseStub = SavePhoneNumUseCaseStub()
    }

    @Test
    fun `test execute saves phone number correctly`() {
        val phoneNum = "+79998887766"
        savePhoneNumUseCaseStub.execute(phoneNum = phoneNum)

        assertEquals(phoneNum, savePhoneNumUseCaseStub.savedPhoneNum)
    }
}