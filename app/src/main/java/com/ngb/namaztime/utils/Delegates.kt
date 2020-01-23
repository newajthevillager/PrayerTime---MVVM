package com.ngb.namaztime.utils

import kotlinx.coroutines.*

// so that coroutine scope can be called inside lazy{}
fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}