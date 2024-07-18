package com.turitsynanton.android.wbtech.di

import com.turitsynanton.android.wbtech.data.repository.RepositoryImpl
import com.turitsynanton.android.wbtech.domain.repository.Repository
import org.koin.dsl.module

val dataModule = module {
    single<Repository> {
        RepositoryImpl()
    }
}