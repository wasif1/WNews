package com.wasif.core.utills.dispatcher

class DefaultDispatcher : DispatcherProvider {
    override fun main() = kotlinx.coroutines.Dispatchers.Main
    override fun io() = kotlinx.coroutines.Dispatchers.IO
    override fun default() = kotlinx.coroutines.Dispatchers.Default
    override fun unconfined() = kotlinx.coroutines.Dispatchers.Unconfined
}