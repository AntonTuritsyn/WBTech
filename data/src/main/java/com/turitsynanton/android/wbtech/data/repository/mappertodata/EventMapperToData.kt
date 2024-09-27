package com.turitsynanton.android.wbtech.data.repository.mappertodata

import com.turitsynanton.android.wbtech.data.storage.models.DataEvent
import com.turitsynanton.android.wbtech.domain.models.DomainEvent

internal class EventMapperToData(
    private val hostMapper: UsersMapperToData,
    private val usersMapper: UsersMapperToData,
    private val tagsMapper: TagsMapperToData
) : IMapperToData<DomainEvent, DataEvent> {
    override fun mapToData(entity: DomainEvent): DataEvent {
        return DataEvent(
            id = entity.id,
            name = entity.name,
            date = entity.date,
            city = entity.city,
            description = entity.description,
            host = hostMapper.mapToData(entity.host),
            participants = entity.participants.map { user ->
                usersMapper.mapToData(user)
            },
            tags = entity.tags.map { tag ->
                tagsMapper.mapToData(tag)
            },
            icon = entity.image
        )
    }
}

internal fun DomainEvent.mapEventToData(mapper: IMapperToData<DomainEvent, DataEvent>): DataEvent {
    return mapper.mapToData(this)
}

internal fun List<DomainEvent>.mapEventsToData(mapper: IMapperToData<DomainEvent, DataEvent>): List<DataEvent> {
    return map { it.mapEventToData(mapper) }
}