package com.turitsynanton.android.wbtech.data.newrepository.newmapper

interface IMapper<in E, out D> {
    fun mapToDomain(entity: E): D
}