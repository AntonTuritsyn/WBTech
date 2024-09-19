package com.turitsynanton.android.wbtech.data.repository.mappertodata

interface IMapperToData<in E, out D> {
    fun mapToData(entity: E): D
}