package com.turitsynanton.android.wbtech.data.repository.mappertodata

import com.turitsynanton.android.wbtech.data.storage.models.DataCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity

internal class CommunityMapperToData(
    private val tagsMapper: TagsMapperToData,
    private val usersMapper: UsersMapperToData,
    private val eventMapper: EventMapperToData
): IMapperToData<DomainCommunity, DataCommunity> {
    override fun mapToData(entity: DomainCommunity): DataCommunity {
        return DataCommunity(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            icon = entity.icon,
            tags = entity.tags.map { tag ->
                tagsMapper.mapToData(tag)
            },
            users = entity.users.map { user ->
                usersMapper.mapToData(user)
            },
            events = entity.events.map { event ->
                eventMapper.mapToData(event)
            }
        )
    }
}

internal fun DomainCommunity.mapCommunityToData(mapper: IMapperToData<DomainCommunity, DataCommunity>): DataCommunity {
    return mapper.mapToData(this)
}

internal fun List<DomainCommunity>.mapCommunityToData(mapper: IMapperToData<DomainCommunity, DataCommunity>): List<DataCommunity> {
    return map { it.mapCommunityToData(mapper) }
}