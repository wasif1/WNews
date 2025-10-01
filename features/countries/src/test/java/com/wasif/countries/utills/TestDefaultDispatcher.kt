package com.wasif.languages.utills

import com.wasif.core.utills.dispatcher.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestDefaultDispatcher : DispatcherProvider {

    @OptIn(ExperimentalCoroutinesApi::class)
    val dispatcherProvider = UnconfinedTestDispatcher()

    override fun main() = dispatcherProvider
    override fun io() = dispatcherProvider
    override fun default() = dispatcherProvider
    override fun unconfined() = dispatcherProvider
}