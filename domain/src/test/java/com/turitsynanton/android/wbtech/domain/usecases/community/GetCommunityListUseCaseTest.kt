package com.turitsynanton.android.wbtech.domain.usecases.community

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.usecases.stubs.GetCommunityListUseCaseStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCommunityListUseCaseTest {

    private lateinit var getCommunityListUseCaseStub: GetCommunityListUseCaseStub

    @BeforeEach
    fun setUp() {
        getCommunityListUseCaseStub = GetCommunityListUseCaseStub()
    }

    @Test
    fun `test execute returns correct community list`() = runTest {
        val expectedCommunities = listOf(
            DomainCommunity(
                id = 1,
                name = "Community 1",
                size = "1000"
            ),
            DomainCommunity(
                id = 2,
                name = "Community 2",
                size = "40"
            )
        )

        getCommunityListUseCaseStub.setCommunityList(expectedCommunities)

        val result = getCommunityListUseCaseStub.execute().first()

        assertEquals(expectedCommunities, result)
    }

    @Test
    fun `test execute returns empty list when no communities`() = runTest {
        getCommunityListUseCaseStub.setCommunityList(emptyList())

        val result = getCommunityListUseCaseStub.execute().first()

        assertEquals(emptyList<DomainCommunity>(), result)
    }
}