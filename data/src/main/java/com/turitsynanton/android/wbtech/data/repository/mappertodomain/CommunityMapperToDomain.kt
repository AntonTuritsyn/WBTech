package com.turitsynanton.android.wbtech.data.repository.mappertodomain

import com.turitsynanton.android.wbtech.data.storage.models.DataCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity

internal class CommunityMapperToDomain(
    private val tagsMapper: TagsMapperToDomain,
    private val usersMapper: UsersMapperToDomain,
    private val eventMapper: EventMapperToDomain
) : IMapperToDomain<DataCommunity, DomainCommunity> {
    override fun mapToDomain(entity: DataCommunity): DomainCommunity {
        return DomainCommunity(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            icon = entity.icon,
            tags = entity.tags.map { tag ->
                tagsMapper.mapToDomain(tag)
            },
            users = entity.users.map { user ->
                usersMapper.mapToDomain(user)
            },
            events = entity.events.map { event ->
                eventMapper.mapToDomain(event)
            }
        )
    }
}

internal fun DataCommunity.mapCommunityToDomain(mapper: IMapperToDomain<DataCommunity, DomainCommunity>): DomainCommunity {
    return mapper.mapToDomain(this)
}

internal fun List<DataCommunity>.mapCommunityToDomain(mapper: IMapperToDomain<DataCommunity, DomainCommunity>): List<DomainCommunity> {
    return map { it.mapCommunityToDomain(mapper) }
}