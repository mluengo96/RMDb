package com.mluengo.rmdb.data.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val rmdbDispatcher: RmdbDispatchers)

enum class RmdbDispatchers {
    Default,
    IO,
}