package com.turitsynanton.android.wbtech.domain.di

import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunityIdByEventIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.community.GetCommunityIdByEventIdUseCase
import com.turitsynanton.android.wbtech.domain.usecases.auth.GetUserUseCase
import com.turitsynanton.android.wbtech.domain.usecases.auth.IGetUserUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.community.GetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.community.GetCommunitiesListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunitiesListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.GetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.GetEventListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IFilterEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.FilterEventsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCommunitiesListUseCase) bind IGetCommunitiesListUseCase::class
    factoryOf(::GetCommunityDetailsUseCase) bind IGetCommunityDetailsUseCase::class

    factoryOf(::GetEventListUseCase) bind IGetEventListUseCase::class
    factoryOf(::GetEventDetailsUseCase) bind IGetEventDetailsUseCase::class

    factoryOf(::FilterEventsUseCase) bind IFilterEventsUseCase::class

    factoryOf(::GetCommunityIdByEventIdUseCase) bind IGetCommunityIdByEventIdUseCase::class

//    factoryOf(::GetCommunityIdByEventIdUseCase) bind IGetCommunityIdByEventIdUseCase::class

/*    factoryOf(::GetUserUseCase) bind IGetUserUseCase::class

    factoryOf(::GetUserUseCase)*/
}