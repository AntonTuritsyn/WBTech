package com.turitsynanton.android.wbtech.data.newrepository.newmapper

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent

internal class EventMapper(
    private val hostMapper: UsersMapper,
    private val usersMapper: UsersMapper,
    private val tagsMapper: TagsMapper,
    private val communityMapper: CommunityMapper
) : IMapper<DataEvent, DomainEvent> {
    override fun mapToDomain(entity: DataEvent): DomainEvent {
        return DomainEvent(
            id = entity.id,
            name = entity.name,
            date = entity.date,
            city = entity.city,
            description = entity.description,
            host = hostMapper.mapToDomain(entity.host),
            organizer = communityMapper.mapToDomain(entity.organizer),
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