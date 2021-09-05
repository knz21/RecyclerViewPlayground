package com.knz21.recyclerviewplayground

import kotlinx.coroutines.flow.*

object EventBus {

    private val innerEvents = MutableSharedFlow<Event>()

    val eventsAsSharedFlow: SharedFlow<Event> get() = innerEvents.asSharedFlow()

    inline fun <reified T> events(): Flow<T> = eventsAsSharedFlow.filter { it is T }.map { it as T }

    suspend fun post(event: Event) {
        innerEvents.emit(event)
    }

    interface Event
}