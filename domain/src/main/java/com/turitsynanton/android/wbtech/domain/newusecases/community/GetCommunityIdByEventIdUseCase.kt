package com.turitsynanton.android.wbtech.domain.newusecases.community

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newrepository.DataListsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCommunityIdByEventIdUseCase(private val dataListsRepository: DataListsRepository) :
    IGetCommunityIdByEventIdUseCase {
    override fun execute(
        eventId: String,
        communities: List<DomainCommunity>
    ): Flow<String>? {
        communities.forEach { community ->
            if (community.events.any {
                    it.id == eventId
                }
            )
                return flow { emit(community.id) }
        }
        return null
    }
}
