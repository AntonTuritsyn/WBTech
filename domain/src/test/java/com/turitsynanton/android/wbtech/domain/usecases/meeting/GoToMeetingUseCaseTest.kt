package com.turitsynanton.android.wbtech.domain.usecases.meeting

import com.turitsynanton.android.wbtech.domain.usecases.stubs.GoToMeetingUseCaseStub
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GoToMeetingUseCaseTest {
    private lateinit var goToMeetingUseCaseStub: GoToMeetingUseCaseStub

    @BeforeEach
    fun setUp() {
        goToMeetingUseCaseStub = GoToMeetingUseCaseStub()
    }

    @Test
    fun `test execute returns true when meeting can be attended`() {
        goToMeetingUseCaseStub.returnValue = true

        val result = goToMeetingUseCaseStub.execute()

        assertTrue(result)
    }

    @Test
    fun `test execute returns false when meeting cannot be attended`() {
        goToMeetingUseCaseStub.returnValue = false

        val result = goToMeetingUseCaseStub.execute()

        assertFalse(result)
    }
}