package com.turitsynanton.android.wbtech.data.repository.mappertodomain

import com.turitsynanton.android.wbtech.data.storage.models.DataUser
import com.turitsynanton.android.wbtech.domain.models.DomainUser

internal class UsersMapperToDomain(
    private val tagsMapper: TagsMapperToDomain
): IMapperToDomain<DataUser, DomainUser> {
    override fun mapToDomain(entity: DataUser): DomainUser {
        return DomainUser(
            id = entity.id,
            name = entity.name,
            city = entity.city,
            phone = entity.phone,
            description = entity.description,
            tags = entity.tags.map { tag ->
                tagsMapper.mapToDomain(tag)
            },
            icon = entity.icon
        )
    }
}

internal fun DataUser.mapUserToDomain(mapper: IMapperToDomain<DataUser, DomainUser>): DomainUser {
    return mapper.mapToDomain(this)
}

internal fun List<DataUser>.mapUserToDomain(mapper: IMapperToDomain<DataUser, DomainUser>): List<DomainUser> {
    return map { it.mapUserToDomain(mapper) }
}