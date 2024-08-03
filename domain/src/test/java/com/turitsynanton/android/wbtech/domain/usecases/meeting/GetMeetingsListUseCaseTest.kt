package com.turitsynanton.android.wbtech.domain.usecases.meeting

import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.usecases.stubs.GetMeetingsListUseCaseStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMeetingsListUseCaseTest {
    private lateinit var getMeetingsListUseCaseStub: GetMeetingsListUseCaseStub

    @BeforeEach
    fun setUp() {
        getMeetingsListUseCaseStub = GetMeetingsListUseCaseStub()
    }

    @Test
    fun `test execute returns correct meetings list`() = runTest {
        val expectedMeetings = listOf(
            DomainMeeting(
                id = "1",
                name = "Meeting 1",
                date = "11.11.24",
                city = "Moscow",
                ended = true,
                tags = emptyList()),
            DomainMeeting(
                id = "2",
                name = "Meeting 2",
                date = "22.01.24",
                city = "Moscow",
                ended = false,
                tags = emptyList())
        )

        getMeetingsListUseCaseStub.setMeetingsList(expectedMeetings)

        val result = getMeetingsListUseCaseStub.execute().first()

        assertEquals(expectedMeetings, result)
    }

    @Test
    fun `test execute returns empty list when no meetings`() = runTest {
        getMeetingsListUseCaseStub.setMeetingsList(emptyList())

        val result = getMeetingsListUseCaseStub.execute().first()

        assertEquals(emptyList<DomainMeeting>(), result)
    }
}