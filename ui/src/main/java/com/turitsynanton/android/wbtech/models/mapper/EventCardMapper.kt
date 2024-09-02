package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.models.UiEventCard

internal class EventCardMapper(
    private val tagMapper: TagMapper
): IMapperToUi<DomainEvent, UiEventCard> {
    override fun mapToUi(entity: DomainEvent): UiEventCard {
        return UiEventCard(
            id = entity.id,
            name = entity.name,
            date = entity.date,
            address = entity.city,
            tags = entity.tags.map { tag ->
                tagMapper.mapToUi(tag)
            }
        )
    }
}

internal fun DomainEvent.mapEventCardToUi(mapper: IMapperToUi<DomainEvent, UiEventCard>): UiEventCard {
    return mapper.mapToUi(this)
}

internal fun List<DomainEvent>.mapEventCardToUi(mapper: IMapperToUi<DomainEvent, UiEventCard>): List<UiEventCard> {
    return map { it.mapEventCardToUi(mapper) }
}