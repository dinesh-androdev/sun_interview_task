package com.sunnetwork

interface TaskCallback<T> {

    fun onComplete(result: T?)
    fun onException(t: Throwable?)

}