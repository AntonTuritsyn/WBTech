package com.turitsynanton.android.wbtech.data.newrepository.newmapper

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity

internal class CommunityMapper(
    private val tagsMapper: TagsMapper,
    private val usersMapper: UsersMapper,
    private val eventMapper: EventMapper
) : IMapper<DataCommunity, DomainCommunity> {
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

internal fun DataCommunity.mapCommunityToDomain(mapper: IMapper<DataCommunity, DomainCommunity>): DomainCommunity {
    return mapper.mapToDomain(this)
}

internal fun List<DataCommunity>.mapCommunityToDomain(mapper: IMapper<DataCommunity, DomainCommunity>): List<DomainCommunity> {
    return map { it.mapCommunityToDomain(mapper) }
}