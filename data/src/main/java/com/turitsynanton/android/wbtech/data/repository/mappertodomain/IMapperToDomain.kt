package com.turitsynanton.android.wbtech.data.repository.mappertodomain

interface IMapperToDomain<in E, out D> {
    fun mapToDomain(entity: E): D
}