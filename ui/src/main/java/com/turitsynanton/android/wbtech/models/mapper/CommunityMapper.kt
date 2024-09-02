package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.models.UiCommunity

internal class CommunityMapper(
    private val tagMapper: TagMapper,
    private val eventCardMapper: EventCardMapper
): IMapperToUi<DomainCommunity, UiCommunity> {
    override fun mapToUi(entity: DomainCommunity): UiCommunity {
        return UiCommunity(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            icon = entity.icon,
            tags = entity.tags.map { tag ->
                tagMapper.mapToUi(tag)
            },
            events = entity.events.map { event ->
                eventCardMapper.mapToUi(event)
            }
        )
    }
}

internal fun DomainCommunity.mapCommunityToUi(mapper: IMapperToUi<DomainCommunity, UiCommunity>): UiCommunity {
    return mapper.mapToUi(this)
}

internal fun List<DomainCommunity>.mapCommunityToUi(mapper: IMapperToUi<DomainCommunity, UiCommunity>): List<UiCommunity> {
    return map { it.mapCommunityToUi(mapper) }
}