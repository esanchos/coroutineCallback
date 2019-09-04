package com.fs.blockingcoroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class Interactor(private val repository: Repository) {

    suspend fun getSubscriptions() = withContext(IO) {
        CompletableDeferred<String>().also { cont ->
            repository.getSubscriptions {
                it?.let {
                    cont.complete(it)
                } ?: cont.cancel("erro from repo")

            }
        }
    }

}