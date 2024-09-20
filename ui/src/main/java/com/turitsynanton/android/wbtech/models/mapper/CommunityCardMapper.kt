package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.models.UiCommunityCard

internal class CommunityCardMapper : IMapperToUi<DomainCommunity, UiCommunityCard> {
    override fun mapToUi(entity: DomainCommunity): UiCommunityCard {
        return UiCommunityCard(
            id = entity.id,
            name = entity.name,
            tags = entity.tags.map { tag ->
                TagMapper().mapToUi(tag)
            },
            image = entity.icon
        )
    }
}

internal fun DomainCommunity.mapCommunityToUi(mapper: IMapperToUi<DomainCommunity, UiCommunityCard>): UiCommunityCard {
    return mapper.mapToUi(this)
}

internal fun List<DomainCommunity>.mapCommunityToUi(mapper: IMapperToUi<DomainCommunity, UiCommunityCard>): List<UiCommunityCard> {
    return map { it.mapCommunityToUi(mapper) }
}