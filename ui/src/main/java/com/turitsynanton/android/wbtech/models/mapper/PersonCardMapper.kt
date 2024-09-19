package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.models.UiPersonCard

internal class PersonCardMapper(
    private val tagMapper: TagMapper
): IMapperToUi<DomainUser, UiPersonCard> {
    override fun mapToUi(entity: DomainUser): UiPersonCard {
        return UiPersonCard(
            id = entity.id,
            name = entity.name,
            tag = tagMapper.mapToUi(entity.tags.first()),
            avatar = entity.icon
        )
    }
}

internal fun DomainUser.mapPersonCardToUi(mapper: IMapperToUi<DomainUser, UiPersonCard>): UiPersonCard {
    return mapper.mapToUi(this)
}

internal fun List<DomainUser>.mapPersonCardToUi(mapper: IMapperToUi<DomainUser, UiPersonCard>): List<UiPersonCard> {
    return map { it.mapPersonCardToUi(mapper) }
}