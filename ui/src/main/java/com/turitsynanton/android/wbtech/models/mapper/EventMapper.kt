package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.domain.models.DomainEvent
import com.turitsynanton.android.wbtech.models.UiEvent

internal class EventMapper(
    private val tagMapper: TagMapper,
    private val hostMapper: HostMapper
): IMapperToUi<DomainEvent, UiEvent> {
    override fun mapToUi(entity: DomainEvent): UiEvent {
        return UiEvent(
            id = entity.id,
            title = entity.name,
            date = entity.date,
            address = entity.city,
            tags = entity.tags.map { tag ->
                tagMapper.mapToUi(tag)
            },
            host = hostMapper.mapToUi(entity.host),
            description = entity.description,
            image = entity.image,
        )
    }
}

internal fun DomainEvent.mapEventToUi(mapper: IMapperToUi<DomainEvent, UiEvent>): UiEvent {
    return mapper.mapToUi(this)
}

internal fun List<DomainEvent>.mapEventToUi(mapper: IMapperToUi<DomainEvent, UiEvent>): List<UiEvent> {
    return map { it.mapEventToUi(mapper) }
}