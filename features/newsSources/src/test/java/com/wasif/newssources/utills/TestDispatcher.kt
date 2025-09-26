package com.wasif.newssources.utills

import com.wasif.core.utills.dispatcher.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestDispatcher : DispatcherProvider {

    @OptIn(ExperimentalCoroutinesApi::class)
    val unconfined = UnconfinedTestDispatcher()

    override fun main() = unconfined
    override fun io() = unconfined
    override fun default() = unconfined
    override fun unconfined() = unconfined
}