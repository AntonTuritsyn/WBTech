package com.turitsynanton.android.wbtech.data.repository.mappertodata

import com.turitsynanton.android.wbtech.data.storage.models.DataUser
import com.turitsynanton.android.wbtech.domain.models.DomainUser

internal class UsersMapperToData(
    private val tagsMapper: TagsMapperToData
) : IMapperToData<DomainUser, DataUser> {
    override fun mapToData(entity: DomainUser): DataUser {
        return DataUser(
            id = entity.id,
            name = entity.name,
            city = entity.city,
            phone = entity.phone,
            description = entity.description,
            tags = entity.tags.map { tag ->
                tagsMapper.mapToData(tag)
            },
            icon = entity.icon
        )
    }
}

internal fun DomainUser.mapUserToData(mapper: IMapperToData<DomainUser, DataUser>): DataUser {
    return mapper.mapToData(this)
}

internal fun List<DomainUser>.mapUsersToData(mapper: IMapperToData<DomainUser, DataUser>): List<DataUser> {
    return map { it.mapUserToData(mapper) }
}