package com.turitsynanton.android.wbtech.data.newrepository.newmapper

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent

internal class EventMapper(
    private val hostMapper: UsersMapper,
    private val usersMapper: UsersMapper,
    private val tagsMapper: TagsMapper
) : IMapper<DataEvent, DomainEvent> {
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

internal fun DataEvent.mapEventToDomain(mapper: IMapper<DataEvent, DomainEvent>): DomainEvent {
    return mapper.mapToDomain(this)
}

internal fun List<DataEvent>.mapEventToDomain(mapper: IMapper<DataEvent, DomainEvent>): List<DomainEvent> {
    return map { it.mapEventToDomain(mapper) }
}