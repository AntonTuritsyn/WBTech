package com.turitsynanton.android.wbtech.domain.usecases.meeting

import com.turitsynanton.android.wbtech.domain.usecases.stubs.CancelMeetingUseCaseStub
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CancelMeetingUseCaseTest {
    private lateinit var cancelMeetingUseCaseStub: CancelMeetingUseCaseStub

    @BeforeEach
    fun setUp() {
        cancelMeetingUseCaseStub = CancelMeetingUseCaseStub()
    }

    @Test
    fun `test execute returns true when meeting is successfully cancelled`() {
        cancelMeetingUseCaseStub.returnValue = true

        val result = cancelMeetingUseCaseStub.execute()

        assertTrue(result)
    }

    @Test
    fun `test execute returns false when meeting cancellation fails`() {
        cancelMeetingUseCaseStub.returnValue = false

        val result = cancelMeetingUseCaseStub.execute()

        assertFalse(result)
    }
}