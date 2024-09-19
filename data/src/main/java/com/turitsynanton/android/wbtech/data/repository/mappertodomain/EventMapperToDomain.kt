package com.turitsynanton.android.wbtech.data.repository.mappertodomain

import com.turitsynanton.android.wbtech.data.storage.models.DataEvent
import com.turitsynanton.android.wbtech.domain.models.DomainEvent

internal class EventMapperToDomain(
    private val hostMapper: UsersMapperToDomain,
    private val usersMapper: UsersMapperToDomain,
    private val tagsMapper: TagsMapperToDomain
) : IMapperToDomain<DataEvent, DomainEvent> {
    override fun mapToDomain(entity: DataEvent): DomainEvent {
        return DomainEvent(
            id = entity.id,
            name = entity.name,
            date = entity.date,
            city = entity.city,
            description = entity.description,
            host = hostMapper.mapToDomain(entity.host),
//            organizer = communityMapper.mapToDomain(entity.organizer),
            participants = entity.participants.map { user ->
                usersMapper.mapToDomain(user)
            },
            tags = entity.tags.map { tag ->
                tagsMapper.mapToDomain(tag)
            },
            icon = entity.icon
        )
    }
}

internal fun DataEvent.mapEventToDomain(mapper: IMapperToDomain<DataEvent, DomainEvent>): DomainEvent {
    return mapper.mapToDomain(this)
}

internal fun List<DataEvent>.mapEventToDomain(mapper: IMapperToDomain<DataEvent, DomainEvent>): List<DomainEvent> {
    return map { it.mapEventToDomain(mapper) }
}