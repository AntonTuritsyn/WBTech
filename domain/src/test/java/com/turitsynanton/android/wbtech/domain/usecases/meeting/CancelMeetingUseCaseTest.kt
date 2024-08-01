package com.turitsynanton.android.wbtech.domain.usecases.meeting

import com.turitsynanton.android.wbtech.domain.usecases.stubs.CancelMeetingUseCaseStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CancelMeetingUseCaseTest {
    private lateinit var cancelMeetingUseCaseStub: CancelMeetingUseCaseStub

    @BeforeEach
    fun setUp() {
        cancelMeetingUseCaseStub = CancelMeetingUseCaseStub()
    }

    @Test
    fun `test execute returns true when meeting is successfully cancelled`() = runTest {
        cancelMeetingUseCaseStub.returnValue = true

        val result = cancelMeetingUseCaseStub.execute().first()

        assertTrue(result)
    }

    @Test
    fun `test execute returns false when meeting cancellation fails`() = runTest {
        cancelMeetingUseCaseStub.returnValue = false

        val result = cancelMeetingUseCaseStub.execute().first()

        assertFalse(result)
    }
}