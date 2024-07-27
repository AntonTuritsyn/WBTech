package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.usecases.stubs.GetCommunityDetailsUseCaseStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCommunityDetailsUseCaseTest {
    private lateinit var getCommunityDetailsUseCaseStub: GetCommunityDetailsUseCaseStub

    @BeforeEach
    fun setUp() {
        getCommunityDetailsUseCaseStub = GetCommunityDetailsUseCaseStub()
    }

    @Test
    fun `test execute returns correct community details`() = runTest {
        val communityId = 1L
        val expectedCommunity =
            DomainCommunity(id = communityId, name = "Test Community", size = "1000")

        getCommunityDetailsUseCaseStub.setCommunityDetails(mapOf(communityId to expectedCommunity))

        val result = getCommunityDetailsUseCaseStub.execute(communityId).first()

        assertEquals(expectedCommunity, result)
    }
}