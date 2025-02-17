package com.turitsynanton.android.wbtech.domain.di

import com.turitsynanton.android.wbtech.domain.usecases.GetVisitorsCountUseCase
import com.turitsynanton.android.wbtech.domain.usecases.IInteractorFullInfoExperiment
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetCommunityIdByEventIdUseCase
import com.turitsynanton.android.wbtech.domain.usecases.experiment.InteractorFullInfoExperiment
import com.turitsynanton.android.wbtech.domain.usecases.experiment.GetEventListUseCaseExperiment
import com.turitsynanton.android.wbtech.domain.usecases.experiment.CombinedEventInfo
import com.turitsynanton.android.wbtech.domain.usecases.experiment.UseCaseInnerGetEventDetails
import com.turitsynanton.android.wbtech.domain.usecases.experiment.GetEventDetailsUseCaseExperiment
import com.turitsynanton.android.wbtech.domain.usecases.community.GetCommunityIdByEventIdUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.GetSubscribersCountUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetSubscribersCountUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetVisitorsCountUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.details.GetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.list.GetCommunitiesListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.details.IGetCommunityDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.list.IGetCommunitiesListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.IIsSubscribedToCommunityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.ISubscribeToCommunityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.IUnsubscribeFromCommunityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.IsSubscribedToCommunityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.SubscribeToCommunityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.community.UnsubscribeFromCommunityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.DisableButtonForPastEventUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.EventsListByCommunityIdUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.GetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetEventDetailsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetEventListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.filter.IFilterEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.filter.FilterEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.GetOtherEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.GetPastEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.GetUpcomingEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IDisableButtonForPastEventUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IEventsListByCommunityIdUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetOtherEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetPastEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IGetUpcomingEventsUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IIsRegisteredForEventUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IUpdateSearchQueryUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.IsRegisteredForEventUseCase
import com.turitsynanton.android.wbtech.domain.usecases.event.UpdateSearchQueryUseCase
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventdetailsscreen.GetEventDetailsUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventdetailsscreen.IGetEventDetailsUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.IInfoEventListScreenInteractor
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.filterlist.FilterEventUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.InfoEventListScreenInteractor
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.communitulist.GetCommunitiesListUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.communityid.GetCommunityIdByEventIdUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.communityid.IGetCommunityIdByEventIdUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.eventlist.GetEventsListUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.filterlist.IFilterEventUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.filterlist.IInnerFilterEventUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.experiment.eventlistscreen.filterlist.InnerFilterEventUseCaseNew
import com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles.GetEventsVisibilityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.myprofile.GetMyProfileUseCase
import com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles.IGetListsVisibilityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.myprofile.IGetMyProfileUseCase
import com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles.GetCommunitiesVisibilityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles.ISetListsVisibilityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles.SetCommunitiesVisibilityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles.SetEventsVisibilityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.participants.GetParticipantsListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.participants.IGetParticipantsListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.ISetRegistrationStepUseCase
import com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.SetRegistrationStepUseCase
import com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.code.ISetCodeQueryInteractor
import com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.code.ISetTimerFieldInteractor
import com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.code.SetCodeQueryInteractor
import com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.code.SetTimerFieldInteractor
import com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.phone.ISetPhoneQueryInteractor
import com.turitsynanton.android.wbtech.domain.usecases.registrationtoevent.phone.SetPhoneQueryInteractor
import com.turitsynanton.android.wbtech.domain.usecases.subscribers.GetSubscribersListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.subscribers.IGetSubscribersListUseCase
import com.turitsynanton.android.wbtech.domain.usecases.userprofile.GetCommunitiesForUserUseCase
import com.turitsynanton.android.wbtech.domain.usecases.userprofile.GetEventsForUserUseCase
import com.turitsynanton.android.wbtech.domain.usecases.userprofile.GetUserFullInfoUseCase
import com.turitsynanton.android.wbtech.domain.usecases.userprofile.IGetCommunitiesForUserUseCase
import com.turitsynanton.android.wbtech.domain.usecases.userprofile.IGetEventsForUserUseCase
import com.turitsynanton.android.wbtech.domain.usecases.userprofile.IGetUserFullInfoUseCase
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

    factoryOf(::InteractorFullInfoExperiment) bind IInteractorFullInfoExperiment::class
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
    factoryOf(::DisableButtonForPastEventUseCase) bind IDisableButtonForPastEventUseCase::class
    factoryOf(::GetEventsForUserUseCase) bind IGetEventsForUserUseCase::class
    factoryOf(::GetCommunitiesForUserUseCase) bind IGetCommunitiesForUserUseCase::class
    factoryOf(::GetMyProfileUseCase) bind IGetMyProfileUseCase::class
    factoryOf(::UpdateSearchQueryUseCase) bind IUpdateSearchQueryUseCase::class
    factoryOf(::IsRegisteredForEventUseCase) bind IIsRegisteredForEventUseCase::class
    factoryOf(::SubscribeToCommunityUseCase) bind ISubscribeToCommunityUseCase::class
    factoryOf(::IsSubscribedToCommunityUseCase) bind IIsSubscribedToCommunityUseCase::class
    factoryOf(::UnsubscribeFromCommunityUseCase) bind IUnsubscribeFromCommunityUseCase::class
    factoryOf(::GetSubscribersCountUseCase) bind IGetSubscribersCountUseCase::class
    factoryOf(::GetVisitorsCountUseCase) bind IGetVisitorsCountUseCase::class
    factoryOf(::SetRegistrationStepUseCase) bind ISetRegistrationStepUseCase::class
    factoryOf(::SetTimerFieldInteractor) bind ISetTimerFieldInteractor::class
    factoryOf(::SetCodeQueryInteractor) bind ISetCodeQueryInteractor::class
    factoryOf(::SetPhoneQueryInteractor) bind ISetPhoneQueryInteractor::class


    factoryOf(::SetEventsVisibilityUseCase) bind ISetListsVisibilityUseCase::class
    factoryOf(::GetEventsVisibilityUseCase) bind IGetListsVisibilityUseCase::class
    factoryOf(::SetCommunitiesVisibilityUseCase) bind ISetListsVisibilityUseCase::class
    factoryOf(::GetCommunitiesVisibilityUseCase) bind IGetListsVisibilityUseCase::class

    factoryOf(::GetEventsListUseCaseNew)/* bind IGetEventsListUseCaseNew::class*/
    factoryOf(::GetCommunitiesListUseCaseNew)/* bind IGetCommunitiesListUseCaseNew::class*/

    factoryOf(::InnerFilterEventUseCaseNew) bind IInnerFilterEventUseCaseNew::class
    factoryOf(::FilterEventUseCaseNew) bind IFilterEventUseCaseNew::class
    factoryOf(::InfoEventListScreenInteractor) bind IInfoEventListScreenInteractor::class

    factoryOf(::GetCommunityIdByEventIdUseCaseNew) bind IGetCommunityIdByEventIdUseCaseNew::class
    factoryOf(::GetEventDetailsUseCaseNew) bind IGetEventDetailsUseCaseNew::class

//    factoryOf(::GetCommunityIdByEventIdUseCase) bind IGetCommunityIdByEventIdUseCase::class
    factoryOf(::EventsListByCommunityIdUseCase) bind IEventsListByCommunityIdUseCase::class
}