package com.turitsynanton.android.wbtech.domain.usecases.meeting

import com.turitsynanton.android.wbtech.domain.usecases.stubs.GoToMeetingUseCaseStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GoToMeetingUseCaseTest {
    private lateinit var goToMeetingUseCaseStub: GoToMeetingUseCaseStub

    @BeforeEach
    fun setUp() {
        goToMeetingUseCaseStub = GoToMeetingUseCaseStub()
    }

    @Test
    fun `test execute returns true when meeting can be attended`() = runTest {
        goToMeetingUseCaseStub.returnValue = true

        val result = goToMeetingUseCaseStub.execute().first()

        assertTrue(result)
    }

    @Test
    fun `test execute returns false when meeting cannot be attended`() = runTest {
        goToMeetingUseCaseStub.returnValue = false

        val result = goToMeetingUseCaseStub.execute().first()

        assertFalse(result)
    }
}