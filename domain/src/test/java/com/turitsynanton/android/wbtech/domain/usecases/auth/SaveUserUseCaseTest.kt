package com.turitsynanton.android.wbtech.domain.usecases.auth

import com.turitsynanton.android.wbtech.domain.usecases.stubs.SaveUserUseCaseStub
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SaveUserUseCaseTest {
    private lateinit var saveUserUseCaseStub: SaveUserUseCaseStub

    @BeforeEach
    fun setUp() {
        saveUserUseCaseStub = SaveUserUseCaseStub()
    }

    @Test
    fun `test execute saves user data correctly`() {
        val name = "Anton"
        val surname = "Turitsyn"
        val phone = "+79998887766"

        saveUserUseCaseStub.execute(name, surname, phone)

        assertEquals(name, saveUserUseCaseStub.savedName)
        assertEquals(surname, saveUserUseCaseStub.savedSurname)
        assertEquals(phone, saveUserUseCaseStub.savedPhone)
    }
}