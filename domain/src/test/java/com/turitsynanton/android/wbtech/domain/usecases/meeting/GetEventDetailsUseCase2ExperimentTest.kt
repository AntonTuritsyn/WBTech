package com.turitsynanton.android.wbtech.domain.usecases.meeting

import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.usecases.stubs.GetMeetingDetailsUseCaseStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@OptIn(ExperimentalCoroutinesApi::class)
class GetEventDetailsUseCase2ExperimentTest {
    private lateinit var getMeetingDetailsUseCaseStub: GetMeetingDetailsUseCaseStub

    @BeforeEach
    fun setUp() {
        getMeetingDetailsUseCaseStub = GetMeetingDetailsUseCaseStub()
    }

    @Test
    fun `test execute returns correct meeting details`() = runTest {
        val meetingId = "1"
        val expectedMeeting = DomainMeeting(
            id = meetingId,
            name = "Meeting 1",
            date = "11.11.24",
            city = "Moscow",
            ended = true,
            tags = emptyList()
        )

        getMeetingDetailsUseCaseStub.setMeetingDetails(mapOf(meetingId to expectedMeeting))

        val result = getMeetingDetailsUseCaseStub.execute(meetingId).first()

        assertEquals(expectedMeeting, result)
    }

    @Test
    fun `test execute throws exception for unknown meeting`() = runTest {
        val unknownMeetingId = "999"

        val exception = assertThrows<GetMeetingDetailsUseCaseStub.MeetingNotFoundException> {
            getMeetingDetailsUseCaseStub.execute(unknownMeetingId).first()
        }

        assertEquals("Meeting $unknownMeetingId not found", exception.message)
    }
}