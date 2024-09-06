package com.turitsynanton.android.wbtech.domain.di

import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunityIdByEventIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.InteractorFullInfoExperiment
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.GetEventListUseCaseExperiment
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.CombinedEventInfo
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.UseCaseInnerGetEventDetails
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.GetEventDetailsUseCaseExperiment
import com.turitsynanton.android.wbtech.domain.newusecases.community.GetCommunityIdByEventIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.community.GetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.community.GetCommunitiesListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunitiesListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.EventsListByCommunityIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.GetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetEventListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IFilterEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.FilterEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.GetOtherEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.GetPastEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.GetUpcomingEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IEventsListByCommunityIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetOtherEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetPastEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.event.IGetUpcomingEventsUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.GetEventDetails
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.GetEventDetailsUseCase2
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.GetEventDetailsUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.GetEventIdUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventdetailsscreen.IGetEventDetailsUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.IInfoEventListScreenInteractor
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist.FilterEventUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.InfoEventListScreenInteractor
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communitulist.IGetCommunitiesListUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communitulist.GetCommunitiesListUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communityid.GetCommunityIdByEventIdUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.communityid.IGetCommunityIdByEventIdUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.eventlist.GetEventsListUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.eventlist.IGetEventsListUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist.IFilterEventUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist.IInnerFilterEventUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.experiment.eventlistscreen.filterlist.InnerFilterEventUseCaseNew
import com.turitsynanton.android.wbtech.domain.newusecases.participants.GetParticipantsListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.participants.IGetParticipantsListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.subscribers.GetSubscribersListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.subscribers.IGetSubscribersListUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.users.GetUserFullInfoUseCase
import com.turitsynanton.android.wbtech.domain.newusecases.users.IGetUserFullInfoUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCommunitiesListUseCase) bind IGetCommunitiesListUseCase::class
    factoryOf(::GetCommunityDetailsUseCase) bind IGetCommunityDetailsUseCase::class

//    factoryOf(::GetEventListUseCase) bind IGetEventListUseCase::class
    factoryOf(::GetEventDetailsUseCase) bind IGetEventDetailsUseCase::class

    factoryOf(::FilterEventsUseCase) bind IFilterEventsUseCase::class

    factoryOf(::GetCommunityIdByEventIdUseCase) bind IGetCommunityIdByEventIdUseCase::class
//    _____________________________________________

    factoryOf(::InteractorFullInfoExperiment)
    factoryOf(::GetEventListUseCaseExperiment) bind IGetEventListUseCase::class
    factoryOf(::CombinedEventInfo)
    factoryOf(::UseCaseInnerGetEventDetails)
    factoryOf(::GetEventDetailsUseCaseExperiment)

    factoryOf(::GetOtherEventsUseCase) bind IGetOtherEventsUseCase::class
    factoryOf(::GetUpcomingEventsUseCase) bind IGetUpcomingEventsUseCase::class
    factoryOf(::GetParticipantsListUseCase) bind IGetParticipantsListUseCase::class
    factoryOf(::GetPastEventsUseCase) bind IGetPastEventsUseCase::class
    factoryOf(::GetSubscribersListUseCase) bind IGetSubscribersListUseCase::class
    factoryOf(::GetUserFullInfoUseCase) bind IGetUserFullInfoUseCase::class

    factoryOf(::GetEventsListUseCaseNew)/* bind IGetEventsListUseCaseNew::class*/
    factoryOf(::GetCommunitiesListUseCaseNew)/* bind IGetCommunitiesListUseCaseNew::class*/

    factoryOf(::InnerFilterEventUseCaseNew) bind IInnerFilterEventUseCaseNew::class
    factoryOf(::FilterEventUseCaseNew) bind IFilterEventUseCaseNew::class
    factoryOf(::InfoEventListScreenInteractor) bind IInfoEventListScreenInteractor::class

    factoryOf(::GetCommunityIdByEventIdUseCaseNew) bind IGetCommunityIdByEventIdUseCaseNew::class
    factoryOf(::GetEventDetailsUseCaseNew) bind IGetEventDetailsUseCaseNew::class

    factoryOf(::GetEventIdUseCase)
    factoryOf(::GetEventDetails)
    factoryOf(::GetEventDetailsUseCase2)
//    factoryOf(::GetCommunityIdByEventIdUseCase) bind IGetCommunityIdByEventIdUseCase::class
    factoryOf(::EventsListByCommunityIdUseCase) bind IEventsListByCommunityIdUseCase::class
/*    factoryOf(::GetUserUseCase) bind IGetUserUseCase::class

    factoryOf(::GetUserUseCase)*/
}