package com.turitsynanton.android.wbtech.data.repository.mapper.communities

import com.turitsynanton.android.wbtech.data.repository.mapper.IMapper
import com.turitsynanton.android.wbtech.data.storage.models.DataCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity

internal class CommunityMapper : IMapper<DataCommunity, DomainCommunity> {
    override fun mapToDomain(entity: DataCommunity): DomainCommunity {
        return DomainCommunity(
            id = entity.id,
            name = entity.name,
            size = entity.size
        )
    }
}

internal fun DataCommunity.mapCommunityToDomain(mapper: IMapper<DataCommunity, DomainCommunity>): DomainCommunity {
    return mapper.mapToDomain(this)
}

internal fun List<DataCommunity>.mapCommunityToDomain(mapper: IMapper<DataCommunity, DomainCommunity>): List<DomainCommunity> {
    return map { it.mapCommunityToDomain(mapper) }
}