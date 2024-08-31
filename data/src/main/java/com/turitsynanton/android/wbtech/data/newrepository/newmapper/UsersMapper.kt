package com.turitsynanton.android.wbtech.data.newrepository.newmapper

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataUser
import com.turitsynanton.android.wbtech.domain.newmodels.DomainUser

internal class UsersMapper(
    private val tagsMapper: TagsMapper
): IMapper<DataUser, DomainUser> {
    override fun mapToDomain(entity: DataUser): DomainUser {
        return DomainUser(
            id = entity.id,
            name = entity.name,
            city = entity.city,
            description = entity.description,
            tags = entity.tags.map { tag ->
                tagsMapper.mapToDomain(tag)
            },
            picture = entity.icon
        )
    }
}

internal fun DataUser.mapUserToDomain(mapper: IMapper<DataUser, DomainUser>): DomainUser {
    return mapper.mapToDomain(this)
}

internal fun List<DataUser>.mapUserToDomain(mapper: IMapper<DataUser, DomainUser>): List<DomainUser> {
    return map { it.mapUserToDomain(mapper) }
}