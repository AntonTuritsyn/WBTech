package com.turitsynanton.android.wbtech.domain.newusecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


data class EventDomainModel(val s: String)
data class CommunityDomainModel(val s: String)

// пример использования
val loadNewEventToDetail = InteractorLoadNewEventToDetail() // 1

fun ads() {
    loadNewEventToDetail.invoke(eventId = 123) // 2
}
//----------------------


// Для того, чтобы запрашивать новую инфу
class InteractorLoadNewEventToDetail : KoinComponent {  // 3.1
    private val innerS: UseCaseInnerGetEventsDetails by inject()

    fun invoke(eventId: Int): Unit {
        innerS.loadNew(eventId)
    }
}
//----------------------

class InteractorRefreshEvents : KoinComponent {  // 3.2
    val innerS: UseCaseInnerGetEventsDetails by inject()

    fun invoke(): Unit {
        innerS.refresh()
    }
}

//DOMAIN inner = никто снаружи не видит
class UseCaseInnerGetEventsDetails() {  // 4

    private val streamEventsWithEventId =
        MutableSharedFlow<Int>() // Flow с id Event по которому мы хотим получить инфу
    private var lastValue: Int? = null

    fun loadNew(eventId: Int) {
        lastValue = eventId
        streamEventsWithEventId.tryEmit(eventId)
    }

    fun refresh() {
        lastValue?.run { streamEventsWithEventId.tryEmit(this) }
    }

    fun observe(): Flow<Int> = streamEventsWithEventId

}
//---------------

class GetEventDetails() : KoinComponent {
    val innerS: UseCaseInnerGetEventsDetails by inject()

    private val eventsPrepared = innerS.observe().mapLatest { it ->
        //поход в репозиторий
        EventDomainModel(it.toString())
    }

    fun invoke(): Flow<EventDomainModel> = eventsPrepared
}

class GetCommunityDetail() : KoinComponent {
    val innerS: UseCaseInnerGetEventsDetails by inject()

    private val eventsPrepared = innerS.observe().map { it ->
        //поход в репозиторий
        CommunityDomainModel(it.toString())
    }

    fun invoke(): Flow<CommunityDomainModel> = eventsPrepared
}

class GetEvents() {
    fun invoke(): Flow<List<EventDomainModel>> = flowOf(listOf(EventDomainModel("kasdnfklsdf")))
}

class InteractorFullInfo : KoinComponent {
    val getEventDetails: GetEventDetails by inject() /// interactor
    val getCommunityDetail: GetCommunityDetail by inject() /// interactor
    val getEvents: GetEvents by inject() /// interactor

    val innerFlow =
        combine(
            getEventDetails.invoke(),
            getCommunityDetail.invoke(),
            getEvents.invoke()
        ) { t1, t2, t3 ->
            "asdasd"
        }

    fun invoke(): Flow<String> = innerFlow
}

// неиспользуемое---------
class LoadGetCommunityDetail() {
    fun invoke(communityId: Int): Unit {
    }
}

class LoadGetEvents() : KoinComponent {

    val details: GetEventDetails by inject()

    fun invoke(communityId: Int): Unit {

    }
}

/*
class GetASDASd {

}*/
